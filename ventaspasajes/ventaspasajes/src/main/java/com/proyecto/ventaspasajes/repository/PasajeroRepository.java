package com.proyecto.ventaspasajes.repository;

import com.proyecto.ventaspasajes.entity.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasajeroRepository extends JpaRepository<Pasajero, Integer> {

    Optional<Pasajero> findByDocumento(String documento);

    List<Pasajero> findByApellidoContaining(String apellido);

    List<Pasajero> findByNombreContaining(String nombre);

}