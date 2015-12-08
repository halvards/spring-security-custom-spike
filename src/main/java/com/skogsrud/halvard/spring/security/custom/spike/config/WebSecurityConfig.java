package com.skogsrud.halvard.spring.security.custom.spike.config;

import com.skogsrud.halvard.spring.security.custom.spike.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/hello.txt").permitAll()  // no authentication on endpoints '/' and '/hello*'
                .anyRequest().authenticated()  // all other endpoints require authentication
                .and()
            .formLogin()
                .loginPage("/login") // custom login page
                .passwordParameter("familyName") // form element name
                .defaultSuccessUrl("/hello.html")
                .permitAll()  // no authentication on logout endpoint
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // allow logout using GET when CSRF protection is enabled
                .logoutSuccessUrl("/hello.txt")
                .permitAll();  // no authentication on logout endpoint
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        PlaintextPasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();
        passwordEncoder.setIgnorePasswordCase(true);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(new CustomUserDetailsService());
        auth
            .authenticationProvider(authenticationProvider);
    }
}
