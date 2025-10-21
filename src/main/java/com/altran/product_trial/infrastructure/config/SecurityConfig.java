package com.altran.product_trial.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // désactive la protection CSRF (utile pour tests API)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // permet toutes les requêtes sans login
                );
        return http.build();
    }
}
