package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.Pasajero;
import com.proyecto.ventaspasajes.service.PasajeroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pasajeros")
@CrossOrigin("*")
public class PasajeroController {

    @Autowired
    private PasajeroService pasajeroService;

    // CLIENTE registra pasajero
    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public Pasajero registrar(@RequestBody Pasajero pasajero) {
        return pasajeroService.registrar(pasajero);
    }

    // ADMIN lista todos los pasajeros
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Pasajero> listar(){
        return pasajeroService.listar();
    }

    // ADMIN elimina pasajero
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Integer id){
        pasajeroService.eliminar(id);
    }

    // ADMIN busca pasajero por id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Pasajero buscar(@PathVariable Integer id){
        return pasajeroService.buscar(id);
    }

}