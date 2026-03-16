package com.proyecto.ventaspasajes.repository;

import com.proyecto.ventaspasajes.entity.Avion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvionRepository extends JpaRepository<Avion, Integer> {

    List<Avion> findByEstado(Avion.EstadoAvion estado);

}
