package com.proyecto.ventaspasajes.repository;

import com.proyecto.ventaspasajes.entity.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsientoRepository extends JpaRepository<Asiento, Integer> {

    List<Asiento> findByAvionIdAvion(Integer idAvion);

    long countByAvionIdAvion(Integer idAvion);

}