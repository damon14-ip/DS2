package com.aeroweb.sistemapasajes.controllers;

import com.aeroweb.sistemapasajes.entities.Asiento;
import com.aeroweb.sistemapasajes.services.AsientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asientos")
@CrossOrigin(origins = "http://localhost:4200")
public class AsientoController {

    @Autowired
    private AsientoService service;

    // ✅ LISTAR TODOS POR AVIÓN
    @GetMapping("/avion/{id}")
    public ResponseEntity<List<Asiento>> listar(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarPorAvion(id));
    }

    // ✅ LISTAR DISPONIBLES
    @GetMapping("/disponibles/{id}")
    public ResponseEntity<List<Asiento>> disponibles(@PathVariable Integer id) {
        return ResponseEntity.ok(service.listarDisponibles(id));
    }

    // ✅ BUSCAR POR CÓDIGO
    @GetMapping("/buscar")
    public ResponseEntity<Asiento> buscar(@RequestParam Integer avionId,
                                          @RequestParam String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Asiento asiento = service.buscarPorCodigo(avionId, codigo);
        return ResponseEntity.ok(asiento);
    }

    // ✅ CREAR ASIENTO
    @PostMapping
    public ResponseEntity<Asiento> crear(@RequestBody Asiento asiento) {
        return ResponseEntity.ok(service.crear(asiento));
    }

    // ✅ OCUPAR ASIENTO
    @PutMapping("/ocupar/{id}")
    public ResponseEntity<String> ocupar(@PathVariable Integer id) {
        service.ocuparAsiento(id);
        return ResponseEntity.ok("Asiento ocupado correctamente");
    }

    // ✅ LIBERAR ASIENTO
    @PutMapping("/liberar/{id}")
    public ResponseEntity<String> liberar(@PathVariable Integer id) {
        service.liberarAsiento(id);
        return ResponseEntity.ok("Asiento liberado correctamente");
    }
}
