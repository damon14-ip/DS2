package com.aeroweb.sistemapasajes.services;
import com.aeroweb.sistemapasajes.dto.ReservaRequestDTO;
import com.aeroweb.sistemapasajes.dto.ReservaRespuestaDTO;

public interface ReservaService {
    ReservaRespuestaDTO procesarReserva(ReservaRequestDTO request, Long usuarioId);
}