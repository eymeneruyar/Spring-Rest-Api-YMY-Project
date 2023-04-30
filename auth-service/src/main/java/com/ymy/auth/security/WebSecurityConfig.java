package com.ymy.auth.security;

import com.ymy.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

    final UserService userService;
    final BasicAuthEntryPoint entryPoint;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //We have added this config method because spring security erase the user password in UserDetails object.
        auth.eraseCredentials(false);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .authenticationEntryPoint(entryPoint)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/registration/**").permitAll()
                .requestMatchers("/api/test/all").permitAll()
                .requestMatchers("/api/test/user").hasAnyRole("ADMIN","USER")
                .requestMatchers("/api/test/admin").hasRole("ADMIN")
                .requestMatchers("/api/v1/company/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }



}
