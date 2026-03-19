package com.aeroweb.sistemapasajes.mappers;

import com.aeroweb.sistemapasajes.entities.Reserva;
import com.aeroweb.sistemapasajes.dto.ReservaRespuestaDTO;

public class ReservaMapper {

    public static ReservaRespuestaDTO toRespuestaDTO(Reserva reserva, String mensaje) {
        if (reserva == null) return null;

        ReservaRespuestaDTO dto = new ReservaRespuestaDTO();
        dto.setPnr(reserva.getPnr());
        dto.setTotalPagado(reserva.getTotalReserva());
        dto.setEstado(reserva.getEstadoPago().name());
        dto.setMensaje(mensaje);
        
        return dto;
    }
}