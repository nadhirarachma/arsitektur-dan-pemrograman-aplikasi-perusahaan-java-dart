package apap.ta.rumahsehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import apap.ta.rumahsehat.config.JwtAuthenticationEntryPoint;
import apap.ta.rumahsehat.config.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsServiceImpl jwtUserDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(encoder());
	}

	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
	public JwtRequestFilter jwtRequestFilter(){
		return new JwtRequestFilter();
	}

	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				.authorizeRequests().antMatchers("/api/v1/login").permitAll().
				// all other requests need to be authenticated
				anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/api/v1/login").permitAll()
            .antMatchers("/api/v1/logout").permitAll()
            // .antMatchers("/api/v1/pasien/profile/**").hasAuthority("Pasien")
            .antMatchers("/api/v1/pasien/profile/{username}").permitAll()
            .antMatchers("/api/v1/get-dokter/**").permitAll()
				.antMatchers("/api/v1/get-appointment-pasien").permitAll()
            .antMatchers("/api/v1/post-appointment").permitAll().antMatchers("/api/v1/get-appointment-detail-pasien").permitAll()
			.antMatchers("/api/v1/pasien/add").permitAll()
            .antMatchers("/api/v1/pasien/topup").permitAll()
            .antMatchers("/api/v1/resep").permitAll()
            .antMatchers("/api/v1/resep/view/{id}").permitAll()
            .antMatchers("/api/v1/resep/jumlah/{id}").permitAll()
            .antMatchers("/resep").hasAnyAuthority("Admin","Apoteker")
            .antMatchers("/resep/view/{id}").hasAnyAuthority("Admin","Apoteker","Dokter")
            .antMatchers("/login-sso", "/validate-ticket").permitAll()
            .antMatchers("/dokter/viewall").hasAuthority("Admin")
            .antMatchers("/dokter/add").hasAuthority("Admin")
            .antMatchers("/resep/add").hasAnyAuthority("Admin", "Dokter")
            .antMatchers("/apoteker/viewall").hasAuthority("Admin")
            .antMatchers("/apoteker/add").hasAuthority("Admin")
            .antMatchers("/pasien/viewall").hasAuthority("Admin")
            .antMatchers("/obat").hasAnyAuthority("Admin","Apoteker")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login").permitAll();
        return http.build();
    }
}