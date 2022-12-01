package apap.ta.rumahsehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import apap.ta.rumahsehat.config.JwtAuthenticationEntryPoint;
import apap.ta.rumahsehat.config.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(userDetailsService)
									.passwordEncoder(encoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public JwtRequestFilter jwtTokenFilter(){
		return new JwtRequestFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.cors().and().csrf().disable()
					.authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/api/v1/login").permitAll()
                    .antMatchers("/api/v1/get-dokter/**").permitAll()
                    .antMatchers("/api/v1/post-appointment").permitAll()
                    .antMatchers("/api/v1/pasien/add").permitAll()
                    .antMatchers("/login-sso", "/validate-ticket").permitAll()
                    .antMatchers("/dokter/viewall").hasAuthority("Admin")
                    .antMatchers("/dokter/add").hasAuthority("Admin")
                    .antMatchers("/resep/add").hasAnyAuthority("Admin", "Dokter")
                    .antMatchers("/apoteker/viewall").hasAuthority("Admin")
                    .antMatchers("/apoteker/add").hasAuthority("Admin")
                    .antMatchers("/pasien/viewall").hasAuthority("Admin")
                    .antMatchers("/obat").hasAnyAuthority("Admin","Apoteker")
                    .antMatchers("/resep").hasAnyAuthority("Admin","Apoteker")
					.anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll();
                    // .and()
                    // .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                    // .and()
					// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            
		// httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);					
	}
    
    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http.cors().and().csrf().disable()
    //         .authorizeRequests()
    //         .antMatchers("/css/**").permitAll()
    //         .antMatchers("/js/**").permitAll()
    //         .antMatchers("/api/v1/get-dokter/**").permitAll()
    //         .antMatchers("/api/v1/post-appointment").permitAll()
    //         .antMatchers("/api/v1/pasien/add").permitAll()
    //         .antMatchers("/login-sso", "/validate-ticket").permitAll()
    //         .antMatchers("/dokter/viewall").hasAuthority("Admin")
    //         .antMatchers("/dokter/add").hasAuthority("Admin")
    //         .antMatchers("/resep/add").hasAnyAuthority("Admin", "Dokter")
    //         .antMatchers("/apoteker/viewall").hasAuthority("Admin")
    //         .antMatchers("/apoteker/add").hasAuthority("Admin")
    //         .antMatchers("/pasien/viewall").hasAuthority("Admin")
    //         .antMatchers("/obat").hasAnyAuthority("Admin","Apoteker")
    //         .antMatchers("/resep").hasAnyAuthority("Admin","Apoteker")
    //         .anyRequest().authenticated()
    //         .and()
    //         .formLogin()
    //         .loginPage("/login").permitAll()
    //         .and()
    //         .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    //         .logoutSuccessUrl("/login").permitAll();
    //     return http.build();
    // }
}