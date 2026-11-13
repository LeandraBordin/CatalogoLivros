package com.leandra.biblioteca.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    // Origem(ns) do front-end React, separadas por vírgula (ex.: http://localhost:5173)
    @Value("${cors.allowed-origins}")
    private List<String> allowedOrigins;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(jwtUtil);

        http
            .cors(Customizer.withDefaults())            // usa o bean corsConfigurationSource()
            .csrf(AbstractHttpConfigurer::disable)      // API stateless com Bearer token
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/error").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // preflight do CORS

                // RBAC: PUBLICO só lê; BIBLIOTECARIO lê/cria/edita; ADMIN também exclui
                .requestMatchers(HttpMethod.GET, "/**")
                    .hasAnyRole("PUBLICO", "BIBLIOTECARIO", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/**")
                    .hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/**")
                    .hasAnyRole("BIBLIOTECARIO", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**")
                    .hasRole("ADMIN")

                .anyRequest().denyAll()                 // PATCH e demais métodos: bloqueados
            )
            // Sem token/token inválido -> 401 (autenticado sem permissão continua 403)
            .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(allowedOrigins);
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
