package com.aeroweb.sistemapasajes.controllers;

import com.aeroweb.sistemapasajes.dto.VueloDTO;
import com.aeroweb.sistemapasajes.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
@CrossOrigin(origins = "http://localhost:4200")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @GetMapping
    public ResponseEntity<List<VueloDTO>> listar() {
        return ResponseEntity.ok(vueloService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<VueloDTO> crear(@RequestBody VueloDTO dto) {
        return ResponseEntity.ok(vueloService.guardarDesdeDTO(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        vueloService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}