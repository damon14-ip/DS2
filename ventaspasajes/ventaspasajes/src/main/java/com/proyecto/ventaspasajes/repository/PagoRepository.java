package com.proyecto.ventaspasajes.repository;

import com.proyecto.ventaspasajes.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Integer> {

    List<Pago> findByReserva_IdReserva(Integer idReserva);

}
