package com.aeroweb.sistemapasajes.repositories;

import com.aeroweb.sistemapasajes.entities.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Integer> {
    
    // CORRECCIÓN: Usamos IdAeropuerto porque así se llama en tu clase Aeropuerto
    List<Avion> findByAeropuertoActual_IdAeropuertoAndEstado(Integer idAeropuerto, Avion.EstadoAvion estado);
}