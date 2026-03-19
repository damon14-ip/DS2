package com.aeroweb.sistemapasajes.security;

import org.springframework.beans.factory.annotation.Autowired; // <--- AÑADIR
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // <--- AÑADIR
import org.springframework.web.cors.CorsConfiguration;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter; // <--- INYECTAR EL FILTRO QUE CREASTE

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(request -> {
                var cfg = new CorsConfiguration();
                cfg.setAllowedOrigins(List.of("http://localhost:4200"));
                cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                cfg.setAllowedHeaders(List.of("*"));
                return cfg;
            }))
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(req -> {
                req.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
                req.requestMatchers("/api/vuelos/**").permitAll();
                // Permitimos reservas para cualquier rol autenticado
                req.requestMatchers("/api/reservas/**").authenticated(); 
                req.requestMatchers(HttpMethod.POST, "/api/aviones/**").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.DELETE, "/api/aviones/**").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.POST, "/api/vuelos/**").hasRole("ADMIN");
             // 👀 VER ASIENTOS (usuarios logueados)
                req.requestMatchers(HttpMethod.GET, "/api/asientos/**").authenticated();

                // 🔧 CREAR / EDITAR / ELIMINAR (solo admin)
                req.requestMatchers(HttpMethod.POST, "/api/asientos/**").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.PUT, "/api/asientos/**").hasRole("ADMIN");
                req.requestMatchers(HttpMethod.DELETE, "/api/asientos/**").hasRole("ADMIN");
                req.anyRequest().authenticated();
                
            })
            // ESTA LÍNEA ES LA QUE FALTA PARA QUE EL TOKEN SE LEA:
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}