package com.example.foodify.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.foodify.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

    .requestMatchers("/api/auth/**").permitAll()

    .requestMatchers(HttpMethod.PATCH, "/api/orders/*/status")
    .hasRole("ADMIN")
    .requestMatchers("/api/orders", "/api/orders/**")
    .hasAnyRole("USER", "ADMIN")

    .requestMatchers(HttpMethod.GET, "/api/restaurants/**")
    .hasAnyRole("USER", "ADMIN")

    .requestMatchers(HttpMethod.GET, "/api/foods/**")
    .hasAnyRole("USER", "ADMIN")


    .requestMatchers(HttpMethod.POST, "/api/restaurants/**")
    .hasRole("ADMIN")

    .requestMatchers(HttpMethod.PUT, "/api/restaurants/**")
    .hasRole("ADMIN")

    .requestMatchers(HttpMethod.DELETE, "/api/restaurants/**")
    .hasRole("ADMIN")

    .anyRequest().authenticated()
)
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}