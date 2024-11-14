package com.example.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer{

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                                .anyRequest().permitAll()  // Permite acceso a todas las rutas
                )
                .cors((corsConfigurer) -> {
                    corsConfigurer.disable();
                })  // Habilitar CORS en Spring Security
                .csrf(csrf -> csrf.disable()); // Deshabilita CSRF temporalmente para pruebas con GraphQL

        return http.build();
    }
     @Override
     public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/graphql")  // This applies to your GraphQL endpoint
                .allowedOrigins("http://localhost:4200")  // Frontend URL
                .allowedMethods("GET", "POST")  // Allow only GET and POST methods
                .allowedHeaders("*")  // Allow any headers
                .allowCredentials(false);  // Allow credentials if needed (e.g., cookies)
    }
}
