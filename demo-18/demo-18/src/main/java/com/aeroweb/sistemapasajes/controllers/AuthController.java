package com.aeroweb.sistemapasajes.controllers;

import com.aeroweb.sistemapasajes.dto.LoginDTO;
import com.aeroweb.sistemapasajes.dto.TokenDTO;
import com.aeroweb.sistemapasajes.entities.Usuario;
import com.aeroweb.sistemapasajes.repositories.UsuarioRepository;
import com.aeroweb.sistemapasajes.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {
        var usuario = usuarioRepository.findByEmail(loginDto.getEmail())
                .orElse(null);

        if (usuario != null && passwordEncoder.matches(loginDto.getPassword(), usuario.getPassword())) {
            // Generamos el token JWT real usando el servicio
            String tokenJWT = tokenService.generarToken(usuario);
            
            return ResponseEntity.ok(new TokenDTO(
                tokenJWT, 
                usuario.getEmail(), 
                usuario.getRol().name()
            ));
        }

        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        // 1. Encriptar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        // 2. Por defecto, si no viene rol, asignamos CLIENTE
        if (usuario.getRol() == null) {
            usuario.setRol(com.aeroweb.sistemapasajes.entities.Rol.CLIENTE);
        }
        
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}