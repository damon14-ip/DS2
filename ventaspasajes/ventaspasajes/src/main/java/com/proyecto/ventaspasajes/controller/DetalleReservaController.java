package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.DetalleReserva;
import com.proyecto.ventaspasajes.service.DetalleReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-reserva")
@CrossOrigin("*")
public class DetalleReservaController {

    @Autowired
    private DetalleReservaService detalleService;

    // CREAR DETALLE
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public DetalleReserva agregar(@RequestBody DetalleReserva detalle){
        return detalleService.agregarDetalle(detalle);
    }

    // LISTAR TODOS
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<DetalleReserva> listar(){
        return detalleService.listarDetalles();
    }

    // LISTAR POR RESERVA
    @GetMapping("/reserva/{id}")
    @PreAuthorize("hasRole('CLIENTE') or hasRole('ADMIN')")
    public List<DetalleReserva> listarPorReserva(@PathVariable Integer id){
        return detalleService.listarPorReserva(id);
    }

}
