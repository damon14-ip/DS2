package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Pago;
import com.proyecto.ventaspasajes.entity.Reserva;
import com.proyecto.ventaspasajes.repository.PagoRepository;
import com.proyecto.ventaspasajes.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public Pago aprobarPago(Integer id){

        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));

        pago.setEstado("APROBADO");

        Reserva reserva = pago.getReserva();
        reserva.setEstado("CONFIRMADA");

        reservaRepository.save(reserva);

        return pagoRepository.save(pago);
    }
}
