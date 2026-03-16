package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Avion;
import com.proyecto.ventaspasajes.repository.AvionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvionService {

    @Autowired
    private AvionRepository avionRepository;

    // ===============================
    // LISTAR AVIONES
    // ===============================
    public List<Avion> listar(){
        return avionRepository.findAll();
    }

    // ===============================
    // BUSCAR AVION
    // ===============================
    public Avion buscar(Integer id){

        return avionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Avión no encontrado"));
    }

    // ===============================
    // REGISTRAR AVION
    // ===============================
    public Avion registrar(Avion avion){

        if(avion.getModelo() == null || avion.getModelo().isEmpty()){
            throw new RuntimeException("Debe ingresar el modelo del avión");
        }

        if(avion.getCapacidadTotal() <= 0){
            throw new RuntimeException("Capacidad del avión inválida");
        }

        // Estado inicial
        avion.setEstado("EN_ESPERA");

        return avionRepository.save(avion);
    }

    // ===============================
    // CAMBIAR ESTADO DEL AVION
    // ===============================
    public Avion cambiarEstado(Integer id, String estado){

        Avion avion = buscar(id);

        if(!estado.equals("EN_ESPERA") &&
           !estado.equals("EN_VUELO") &&
           !estado.equals("MANTENIMIENTO")){
            throw new RuntimeException("Estado no válido");
        }

        avion.setEstado(estado);

        return avionRepository.save(avion);
    }

    // ===============================
    // ELIMINAR AVION
    // ===============================
    public void eliminar(Integer id){

        if(!avionRepository.existsById(id)){
            throw new RuntimeException("El avión no existe");
        }

        avionRepository.deleteById(id);
    }

}