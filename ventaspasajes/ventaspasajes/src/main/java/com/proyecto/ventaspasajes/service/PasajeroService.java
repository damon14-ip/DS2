package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Pasajero;
import com.proyecto.ventaspasajes.repository.PasajeroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PasajeroService {

    @Autowired
    private PasajeroRepository pasajeroRepository;

    public Pasajero registrar(Pasajero pasajero){

        int edad = Period.between(
                pasajero.getFechaNacimiento(),
                LocalDate.now()).getYears();

        if(edad < 0){
            throw new RuntimeException("Fecha inválida");
        }

        return pasajeroRepository.save(pasajero);
    }

    public List<Pasajero> listar(){
        return pasajeroRepository.findAll();
    }

    public void eliminar(Integer id){
        pasajeroRepository.deleteById(id);
    }

    public Pasajero buscar(Integer id){
        return pasajeroRepository.findById(id).orElse(null);
    }

}