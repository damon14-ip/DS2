package com.aeroweb.sistemapasajes.controllers;

import com.aeroweb.sistemapasajes.dto.UsuarioDTO;
import com.aeroweb.sistemapasajes.entities.Usuario;
import com.aeroweb.sistemapasajes.services.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
// Permitimos que Angular (4200) se comunique con el Backend
@CrossOrigin(origins = "http://localhost:4200") 
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener lista de usuarios (mapeados a DTO)
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    // Registrar nuevo usuario con validación de campos
    @PostMapping
    public ResponseEntity<String> crear(@RequestBody @Valid Usuario usuario) {
        // El servicio lanzará excepciones (409 Conflict o 400 Bad Request) si el DNI falla
        usuarioService.registrarNuevoUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario guardado con éxito");
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}