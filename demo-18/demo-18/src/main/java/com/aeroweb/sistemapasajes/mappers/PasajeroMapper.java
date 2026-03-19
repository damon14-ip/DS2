package com.aeroweb.sistemapasajes.mappers;

import com.aeroweb.sistemapasajes.entities.Pasajero;
import com.aeroweb.sistemapasajes.dto.PasajeroDTO;
import java.time.LocalDate;
import java.time.Period;

public class PasajeroMapper {

    public static PasajeroDTO toDTO(Pasajero entidad) {
        if (entidad == null) return null;

        PasajeroDTO dto = new PasajeroDTO();
        dto.setId(entidad.getIdPasajero());
        dto.setNombre(entidad.getNombre());
        dto.setApellido(entidad.getApellido());
        dto.setDocumento(entidad.getNumDocumento());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());

        if (entidad.getFechaNacimiento() != null) {
            int edad = Period.between(entidad.getFechaNacimiento(), LocalDate.now()).getYears();
            dto.setEdad(edad);
            dto.setCategoria(edad < 2 ? "INFANTE" : (edad < 12 ? "NIÑO" : "ADULTO"));
        }

        return dto;
    }

    public static Pasajero toEntity(PasajeroDTO dto) {
        if (dto == null) return null;
        Pasajero entidad = new Pasajero();
        entidad.setNombre(dto.getNombre());
        entidad.setApellido(dto.getApellido());
        entidad.setNumDocumento(dto.getDocumento());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        return entidad;
    }
}