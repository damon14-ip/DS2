package com.aeroweb.sistemapasajes.controllers;

import com.aeroweb.sistemapasajes.dto.AvionDTO;
import com.aeroweb.sistemapasajes.entities.Avion;
import com.aeroweb.sistemapasajes.services.AvionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/aviones")
@CrossOrigin(origins = "http://localhost:4200")
public class AvionController {

    @Autowired private AvionService avionService;

    @GetMapping
    public List<Avion> listar() { 
        return avionService.listarTodos(); 
    }

    // Endpoint de filtrado para el formulario de Vuelos
    @GetMapping("/aeropuerto/{id}")
    public List<Avion> listarPorAeropuerto(@PathVariable Integer id) {
        // Importante: El ID que llega aquí debe ser el 'id' del aeropuerto
        return avionService.listarPorAeropuertoYEstado(id, Avion.EstadoAvion.ACTIVO);
    }

    @PostMapping
    public ResponseEntity<Avion> crear(@RequestBody AvionDTO dto) {
        return ResponseEntity.ok(avionService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avion> editar(@PathVariable Integer id, @RequestBody AvionDTO dto) {
        return ResponseEntity.ok(avionService.actualizar(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avion> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(avionService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        avionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}