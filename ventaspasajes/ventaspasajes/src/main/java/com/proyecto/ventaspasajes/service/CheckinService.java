package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Checkin;
import com.proyecto.ventaspasajes.repository.CheckinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckinService {

    @Autowired
    private CheckinRepository checkinRepository;

    public Checkin realizarCheckin(Checkin checkin){

        if(!checkin.getDetalleReserva()
                .getReserva()
                .getEstado()
                .equals("CONFIRMADA")){
            throw new RuntimeException("La reserva no está confirmada");
        }

        checkin.setEstado("REALIZADO");

        checkin.setTarjetaEmbarque(
                "boardingpass_" + UUID.randomUUID()
        );

        return checkinRepository.save(checkin);
    }
}
