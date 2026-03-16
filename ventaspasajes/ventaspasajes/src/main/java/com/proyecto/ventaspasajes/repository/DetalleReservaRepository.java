package com.proyecto.ventaspasajes.repository;

import com.proyecto.ventaspasajes.entity.DetalleReserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleReservaRepository extends JpaRepository<DetalleReserva, Integer>{

    List<DetalleReserva> findByReservaIdReserva(Integer idReserva);

}
