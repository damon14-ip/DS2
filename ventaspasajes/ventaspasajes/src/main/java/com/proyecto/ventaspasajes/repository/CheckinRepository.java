package com.proyecto.ventaspasajes.repository;

import com.proyecto.ventaspasajes.entity.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Checkin, Integer> {

    Optional<Checkin> findByDetalleReserva_IdDetalle(Integer idDetalle);

}
