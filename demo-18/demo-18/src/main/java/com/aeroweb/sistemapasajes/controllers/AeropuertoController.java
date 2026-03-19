package com.aeroweb.sistemapasajes.controllers;

import com.aeroweb.sistemapasajes.dto.AeropuertoDTO;
import com.aeroweb.sistemapasajes.services.AeropuertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/aeropuertos")
@CrossOrigin(origins = "http://localhost:4200")
public class AeropuertoController {

    @Autowired 
    private AeropuertoService service;

    @GetMapping
    public List<AeropuertoDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<AeropuertoDTO> crear(@RequestBody AeropuertoDTO dto) {
        return ResponseEntity.ok(service.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AeropuertoDTO> actualizar(@PathVariable Integer id, @RequestBody AeropuertoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}