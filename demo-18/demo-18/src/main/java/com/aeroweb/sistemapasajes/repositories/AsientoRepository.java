package com.aeroweb.sistemapasajes.repositories;

import com.aeroweb.sistemapasajes.entities.Asiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AsientoRepository extends JpaRepository<Asiento, Integer> {

    List<Asiento> findByAvionIdAvion(Integer avionId);

    List<Asiento> findByAvionIdAvionAndOcupadoFalse(Integer avionId);

    boolean existsByAvionIdAvionAndCodigoAsiento(Integer avionId, String codigoAsiento);

    // 🔥 CORREGIDO (antes estaba mal)
    
    Optional<Asiento> findByAvionIdAvionAndCodigoAsiento(Integer avionId, String codigoAsiento);

}
