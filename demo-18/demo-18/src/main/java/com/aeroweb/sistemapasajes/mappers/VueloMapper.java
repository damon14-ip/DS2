package com.aeroweb.sistemapasajes.mappers;

import com.aeroweb.sistemapasajes.dto.VueloDTO;
import com.aeroweb.sistemapasajes.entities.Vuelo;

import java.time.format.DateTimeFormatter;

public class VueloMapper {

    public static VueloDTO toDTO(Vuelo v) {
        if (v == null) return null;

        VueloDTO dto = new VueloDTO();

        dto.setIdVuelo(v.getIdVuelo());

        if (v.getAvion() != null) {
            dto.setIdAvion(v.getAvion().getIdAvion());
        }

        if (v.getOrigen() != null) {
            dto.setIdAeropuertoOrigen(v.getOrigen().getIdAeropuerto());

            // 🔥 CLAVE
            dto.setNombreOrigen(v.getOrigen().getNombre());
        }

        if (v.getDestino() != null) {
            dto.setIdAeropuertoDestino(v.getDestino().getIdAeropuerto());

            // 🔥 CLAVE
            dto.setNombreDestino(v.getDestino().getNombre());
        }

        // ✅ FECHA FORMATEADA
        if (v.getFechaSalida() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            dto.setFechaSalida(v.getFechaSalida().format(formatter));
        }

        dto.setPrecioBase(v.getPrecioBase());

        // 🔥 CLAVE (ASIENTOS)
        dto.setAsientosDisponibles(v.getAsientosDisponibles());

        return dto;
    }
}