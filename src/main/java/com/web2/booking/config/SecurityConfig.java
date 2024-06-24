package com.web2.booking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    static final String PATTERN_API_CUSTOMERS = "/api/customers";
    static final String PATTERN_API_PRODUCTS = "/api/products";
    static final String PATTERN_API_ESTABLISHMENTS = "/api/establishment/";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, PATTERN_API_CUSTOMERS).permitAll()
                        .requestMatchers(HttpMethod.GET, PATTERN_API_CUSTOMERS).permitAll()
                        .requestMatchers(HttpMethod.GET, PATTERN_API_CUSTOMERS + "/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, PATTERN_API_CUSTOMERS + "/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, PATTERN_API_CUSTOMERS + "/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, PATTERN_API_PRODUCTS).permitAll()
                        .requestMatchers(HttpMethod.POST, PATTERN_API_PRODUCTS).permitAll()
                        .requestMatchers(HttpMethod.GET, PATTERN_API_PRODUCTS + "/{id}").authenticated()
                        .requestMatchers(HttpMethod.PUT, PATTERN_API_PRODUCTS + "/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, PATTERN_API_PRODUCTS + "/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, PATTERN_API_ESTABLISHMENTS).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
