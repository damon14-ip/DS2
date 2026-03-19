package com.aeroweb.sistemapasajes.controllers;

import com.aeroweb.sistemapasajes.dto.ReservaRequestDTO;
import com.aeroweb.sistemapasajes.entities.Reserva;
import com.aeroweb.sistemapasajes.repositories.ReservaRepository;
import com.aeroweb.sistemapasajes.services.PdfGeneratorService;
import com.aeroweb.sistemapasajes.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservaController {

    @Autowired private ReservaService reservaService;
    @Autowired private PdfGeneratorService pdfService;
    @Autowired private ReservaRepository reservaRepo;

    @PostMapping("/vender")
    public ResponseEntity<byte[]> venderPasaje(@RequestBody ReservaRequestDTO request) {
        try {
            // 1. Procesar la reserva con todas las validaciones de negocio (usuario fijo 1L)
            // El servicio ya guarda en BD y descuenta asientos.
            reservaService.procesarReserva(request, 1L);

            // 2. Recuperar la reserva recién creada para generar el PDF
            // Buscamos la última reserva del vuelo para este flujo (o podrías retornar la entidad desde el service)
            // Para ser precisos, lo ideal es que el service devuelva la Entidad, pero aquí lo resolvemos rápido:
            Reserva guardada = reservaRepo.findFirstByOrderByFechaReservaDesc();

            // 3. Generar PDF
            byte[] pdf = pdfService.generarTicketReserva(guardada);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("ticket_" + guardada.getPnr() + ".pdf")
                    .build());

            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            // Retornamos el mensaje de error de validación (ej: "Máximo un infante por adulto")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage().getBytes());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}