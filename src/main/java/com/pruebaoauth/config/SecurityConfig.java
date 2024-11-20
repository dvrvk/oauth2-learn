package com.pruebaoauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilitar CSRF para desarrollo (puedes habilitarlo en producción)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/api/usuarios/**", "/h2-console/**").permitAll()  // Permitir acceso sin autenticación a /users
                        .anyRequest().authenticated()
                )
                .oauth2Login(
                        Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .permitAll() // Allow access to the login page without authentication
                );

        return http.build();
    }
}

