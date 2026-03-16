package com.proyecto.ventaspasajes.controller;

import com.proyecto.ventaspasajes.entity.Checkin;
import com.proyecto.ventaspasajes.service.CheckinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkin")
@CrossOrigin("*")
public class CheckinController {

    @Autowired
    private CheckinService checkinService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public Checkin realizar(@RequestBody Checkin checkin) {
        return checkinService.realizarCheckin(checkin);
    }
}
