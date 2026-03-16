package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Reserva;
import com.proyecto.ventaspasajes.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // =========================
    // CREAR RESERVA
    // =========================
    public Reserva crearReserva(Reserva reserva){

        reserva.setCodigoReserva(
                UUID.randomUUID().toString().substring(0,6).toUpperCase()
        );

        reserva.setEstado("PENDIENTE");

        reserva.setFechaExpiracion(
                LocalDateTime.now().plusHours(24)
        );

        return reservaRepository.save(reserva);
    }

    // =========================
    // LISTAR RESERVAS
    // =========================
    public List<Reserva> listarReservas(){
        return reservaRepository.findAll();
    }

    // =========================
    // CONFIRMAR RESERVA
    // =========================
    public Reserva confirmarReserva(Integer id){

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado("CONFIRMADA");

        return reservaRepository.save(reserva);
    }

}