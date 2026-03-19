package com.aeroweb.sistemapasajes.controllers;

import com.aeroweb.sistemapasajes.dto.DashboardDTO;
import com.aeroweb.sistemapasajes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/stats")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    @Autowired private AvionRepository avionRepo;
    @Autowired private VueloRepository vueloRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private AeropuertoRepository aeropuertoRepo;

    @GetMapping
    public ResponseEntity<DashboardDTO> getStats() {
        var stats = new DashboardDTO(
            avionRepo.count(),
            vueloRepo.count(),
            usuarioRepo.count(),
            aeropuertoRepo.count()
        );
        return ResponseEntity.ok(stats);
    }
}