package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.Avion;
import com.proyecto.ventaspasajes.service.AvionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aviones")
@CrossOrigin("*")
public class AvionController {

    @Autowired
    private AvionService avionService;

    // ===============================
    // ADMIN y CLIENTE pueden ver aviones
    // ===============================
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    public List<Avion> listar(){
        return avionService.listar();
    }

    // ===============================
    // ADMIN y CLIENTE pueden ver detalle
    // ===============================
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    public Avion buscar(@PathVariable Integer id){
        return avionService.buscar(id);
    }

    // ===============================
    // ADMIN crea avion
    // ===============================
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Avion registrar(@RequestBody Avion avion){
        return avionService.registrar(avion);
    }

    // ===============================
    // ADMIN cambia estado del avion
    // ===============================
    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('ADMIN')")
    public Avion cambiarEstado(@PathVariable Integer id,
                               @RequestParam String estado){
        return avionService.cambiarEstado(id, estado);
    }

    // ===============================
    // ADMIN elimina avion
    // ===============================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Integer id){
        avionService.eliminar(id);
    }

}