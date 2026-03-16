package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.Vuelo;
import com.proyecto.ventaspasajes.service.VueloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
@CrossOrigin("*")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    // LISTAR VUELOS – ADMIN o CLIENTE
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public List<Vuelo> listar() {
        return vueloService.listar();
    }

    // PROGRAMA VUELO – solo ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Vuelo programar(@RequestBody Vuelo vuelo) {
        return vueloService.programarVuelo(vuelo);
    }

    // CAMBIAR ESTADO – solo ADMIN
    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public void cambiarEstado(
            @PathVariable Integer id,
            @RequestParam Vuelo.EstadoVuelo estado) {
        vueloService.cambiarEstado(id, estado);
    }
}
