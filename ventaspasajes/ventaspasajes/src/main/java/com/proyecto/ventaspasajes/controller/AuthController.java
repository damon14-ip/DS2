package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.dto.LoginRequest;
import com.proyecto.ventaspasajes.security.CustomUserDetailsService;
import com.proyecto.ventaspasajes.security.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(request.getEmail());

            String token = jwtUtil.generarToken(userDetails);
            String rol = userDetails.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("token", token);
            respuesta.put("email", userDetails.getUsername());
            respuesta.put("rol", rol);
            respuesta.put("mensaje", "Login exitoso");

            return ResponseEntity.ok(respuesta);

        } catch (BadCredentialsException e) {

            Map<String, String> error = new HashMap<>();
            error.put("mensaje", "Credenciales incorrectas");

            return ResponseEntity.status(401).body(error);
        }
    }
}
