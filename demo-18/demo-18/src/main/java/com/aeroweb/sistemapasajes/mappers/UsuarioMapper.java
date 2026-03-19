package com.aeroweb.sistemapasajes.mappers;

import com.aeroweb.sistemapasajes.entities.Usuario;
import com.aeroweb.sistemapasajes.dto.UsuarioDTO;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario entidad) {
        if (entidad == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entidad.getIdUsuario());
        
        String nombre = (entidad.getNombre() != null) ? entidad.getNombre() : "";
        String apellido = (entidad.getApellido() != null) ? entidad.getApellido() : "";
        dto.setNombreCompleto(nombre + " " + apellido);
        
        dto.setEmail(entidad.getEmail());
        dto.setDni(entidad.getDni());
        dto.setRol(entidad.getRol() != null ? entidad.getRol().name() : null);
        
        return dto;
    }
}