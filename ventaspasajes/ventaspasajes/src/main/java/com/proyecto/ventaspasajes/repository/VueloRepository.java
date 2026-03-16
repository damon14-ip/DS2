package com.proyecto.ventaspasajes.repository;

import com.proyecto.ventaspasajes.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VueloRepository extends JpaRepository<Vuelo, Integer> {

    List<Vuelo> findByAeropuertoOrigen_IdAeropuertoAndAeropuertoDestino_IdAeropuertoAndFecha(
            Integer origen,
            Integer destino,
            LocalDate fecha
    );

}
