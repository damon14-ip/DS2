package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.Aeropuerto;
import com.proyecto.ventaspasajes.service.AeropuertoService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aeropuertos")
@CrossOrigin("*")
public class AeropuertoController {

    private final AeropuertoService aeropuertoService;

    public AeropuertoController(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    public List<Aeropuerto> listar() {
        return aeropuertoService.listar();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Aeropuerto buscar(@PathVariable Integer id) {
        return aeropuertoService.buscarPorId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Aeropuerto guardar(@RequestBody Aeropuerto aeropuerto) {
        return aeropuertoService.guardar(aeropuerto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Aeropuerto actualizar(@PathVariable Integer id,
                                 @RequestBody Aeropuerto aeropuerto) {
        return aeropuertoService.actualizar(id, aeropuerto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Integer id) {
        aeropuertoService.eliminar(id);
    }
}
