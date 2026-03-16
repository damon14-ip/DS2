package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Vuelo;
import com.proyecto.ventaspasajes.repository.VueloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    public Vuelo programarVuelo(Vuelo vuelo){

        if(vuelo.getAeropuertoOrigen().getIdAeropuerto()
                .equals(vuelo.getAeropuertoDestino().getIdAeropuerto())){
            throw new RuntimeException("Origen y destino no pueden ser iguales");
        }

        if(vuelo.getHoraLlegada().isBefore(vuelo.getHoraSalida())){
            throw new RuntimeException("Hora llegada inválida");
        }

        vuelo.setEstado(Vuelo.EstadoVuelo.PROGRAMADO);

        return vueloRepository.save(vuelo);
    }

    public List<Vuelo> listar(){
        return vueloRepository.findAll();
    }

    public void cambiarEstado(Integer id, Vuelo.EstadoVuelo estado){

        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        vuelo.setEstado(estado);

        vueloRepository.save(vuelo);
    }
}
