package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.Asiento;
import com.proyecto.ventaspasajes.service.AsientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asientos")
@CrossOrigin("*")
public class AsientoController {

    @Autowired
    private AsientoService asientoService;

    // ADMIN: ver todos los asientos
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Asiento> listar() {
        return asientoService.listar();
    }

    // CLIENTE y ADMIN: ver asientos de un avión
    @GetMapping("/avion/{idAvion}")
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    public List<Asiento> listarPorAvion(@PathVariable Integer idAvion) {
        return asientoService.listarPorAvion(idAvion);
    }

    // ADMIN: crear asiento
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Asiento guardar(@RequestBody Asiento asiento) {
        return asientoService.guardar(asiento);
    }

    // ADMIN: eliminar asiento
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Integer id) {
        asientoService.eliminar(id);
    }
}