package com.proyecto.ventaspasajes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          CorsConfigurationSource corsConfigurationSource) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // SOLO para pruebas
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // LOGIN Y REGISTRO
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/usuarios/registro").permitAll()

                // ADMIN Y CLIENTE
                .requestMatchers("/api/vuelos/**").hasAnyRole("ADMIN","CLIENTE")
                .requestMatchers("/api/aeropuertos/**").hasAnyRole("ADMIN","CLIENTE")

                // SOLO CLIENTE
                .requestMatchers("/api/reservas/**").hasAnyRole("ADMIN","CLIENTE")
                .requestMatchers("/api/pasajeros/**").hasAnyRole("ADMIN","CLIENTE")
                .requestMatchers("/api/detalle-reserva/**").hasRole("CLIENTE")
                .requestMatchers("/api/checkin/**").hasRole("CLIENTE")
                .requestMatchers("/api/pagos/**").hasRole("CLIENTE")

                // SOLO ADMIN
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                .requestMatchers("/api/aviones/**").hasRole("ADMIN")
                .requestMatchers("/api/asientos/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            )

            .userDetailsService(userDetailsService)

            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}