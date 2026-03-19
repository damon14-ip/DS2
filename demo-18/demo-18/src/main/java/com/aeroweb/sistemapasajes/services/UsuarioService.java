package com.aeroweb.sistemapasajes.services;

import com.aeroweb.sistemapasajes.dto.UsuarioDTO;
import com.aeroweb.sistemapasajes.entities.Usuario;
import com.aeroweb.sistemapasajes.mappers.UsuarioMapper;
import com.aeroweb.sistemapasajes.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepo.findAll().stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void registrarNuevoUsuario(Usuario usuario) {
        // 1. Validar longitud del DNI (Doble check de seguridad)
        if (usuario.getDni() == null || usuario.getDni().length() != 8) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El DNI debe tener exactamente 8 dígitos");
        }

        // 2. Validar si el DNI ya existe
        if (usuarioRepo.existsByDni(usuario.getDni())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El DNI ya se encuentra registrado");
        }

        // 3. Validar si el Email ya existe
        if (usuarioRepo.existsByEmail(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El correo electrónico ya está en uso");
        }

        // 4. Encriptar clave y guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepo.save(usuario);
    }

    public void eliminar(Long id) {
        if (!usuarioRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
        }
        usuarioRepo.deleteById(id);
    }
}