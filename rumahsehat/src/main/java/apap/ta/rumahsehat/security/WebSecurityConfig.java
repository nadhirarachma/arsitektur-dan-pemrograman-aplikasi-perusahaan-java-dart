package apap.ta.rumahsehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/api/v1/get-dokter/**").permitAll()
            .antMatchers("/api/v1/post-appointment").permitAll()
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
        return http.build();
    }

    // @Bean
    // public BCryptPasswordEncoder encoder() {
    //     return new BCryptPasswordEncoder();
    // }

    public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

}