package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.DetalleReserva;
import com.proyecto.ventaspasajes.repository.DetalleReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleReservaService {

    @Autowired
    private DetalleReservaRepository detalleRepository;

    // AGREGAR DETALLE DE RESERVA
    public DetalleReserva agregarDetalle(DetalleReserva detalle){

        if(detalle.getReserva() == null){
            throw new RuntimeException("Debe seleccionar una reserva");
        }

        if(detalle.getVuelo() == null){
            throw new RuntimeException("Debe seleccionar un vuelo");
        }

        if(detalle.getPasajero() == null){
            throw new RuntimeException("Debe seleccionar un pasajero");
        }

        if(detalle.getAsiento() == null){
            throw new RuntimeException("Debe seleccionar un asiento");
        }

        double precioBase = detalle.getVuelo().getPrecioBase();
        double precioExtra = detalle.getAsiento().getPrecio();

        double precioFinal = precioBase + precioExtra;

        detalle.setPrecioFinal(precioFinal);

        return detalleRepository.save(detalle);
    }

    // LISTAR TODOS LOS DETALLES
    public List<DetalleReserva> listarDetalles(){
        return detalleRepository.findAll();
    }

    // LISTAR DETALLES POR RESERVA
    public List<DetalleReserva> listarPorReserva(Integer idReserva){
        return detalleRepository.findByReservaIdReserva(idReserva);
    }

    // ELIMINAR DETALLE
    public void eliminarDetalle(Integer id){
        detalleRepository.deleteById(id);
    }

}
