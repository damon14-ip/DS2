package com.aeroweb.sistemapasajes.repositories;

import com.aeroweb.sistemapasajes.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Busca por email (usado para el login)
    Optional<Usuario> findByEmail(String email);
    
    // Verifica si el email ya existe
    boolean existsByEmail(String email);
    
    // Verifica si el DNI ya existe (Nuevo para tu validación)
    boolean existsByDni(String dni);
}