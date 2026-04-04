package com.davidan.SmartMirrorAPI.api_related.auth;

import org.apache.catalina.util.CustomObjectInputStream;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.davidan.SmartMirrorAPI.api_related.auth.JWT.JWTFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JWTFilter jWTFilter = new JWTFilter();

  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(c -> c.disable())
      .authorizeHttpRequests(customizer -> {
        customizer.requestMatchers("/auth/login", "/auth/refresh").permitAll();
        customizer.anyRequest().authenticated();
      })
      .addFilterBefore(jWTFilter, UsernamePasswordAuthenticationFilter.class)
      .httpBasic(Customizer.withDefaults())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    return http.build();
  }
}
