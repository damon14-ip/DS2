package com.proyecto.ventaspasajes.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
            "clave_super_secreta_12345678901234567890";

    private static final long EXPIRACION_TOKEN = 86400000; // 1 día

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generar token
    public String generarToken(UserDetails userDetails) {

        String rol = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("rol", rol)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACION_TOKEN))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Obtener email
    public String extraerCorreo(String token) {
        return extraerClaims(token).getSubject();
    }

    // Obtener rol
    public String extraerRol(String token) {
        return extraerClaims(token).get("rol", String.class);
    }

    // Validar token
    public boolean validarToken(String token, UserDetails userDetails) {

        String correo = extraerCorreo(token);

        return correo.equals(userDetails.getUsername())
                && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        return extraerClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
