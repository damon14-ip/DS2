package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.Reserva;
import com.proyecto.ventaspasajes.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin("*")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // =========================
    // CREAR RESERVA
    // =========================
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public Reserva crear(@RequestBody Reserva reserva) {
        return reservaService.crearReserva(reserva);
    }

    // =========================
    // LISTAR RESERVAS
    // =========================
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Reserva> listar() {
        return reservaService.listarReservas();
    }

    // =========================
    // CONFIRMAR RESERVA
    // =========================
    @PutMapping("/{id}/confirmar")
    @PreAuthorize("hasRole('ADMIN')")
    public Reserva confirmar(@PathVariable Integer id) {
        return reservaService.confirmarReserva(id);
    }

}