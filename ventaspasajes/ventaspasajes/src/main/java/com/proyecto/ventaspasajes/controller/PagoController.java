package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.Pago;
import com.proyecto.ventaspasajes.service.PagoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin("*")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PutMapping("/{id}/aprobar")
    @PreAuthorize("hasRole('ADMIN')")
    public Pago aprobar(@PathVariable Integer id) {
        return pagoService.aprobarPago(id);
    }
}
