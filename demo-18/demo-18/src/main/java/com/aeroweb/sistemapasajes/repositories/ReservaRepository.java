package com.aeroweb.sistemapasajes.repositories;

import com.aeroweb.sistemapasajes.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findByPnr(String pnr);
    Reserva findFirstByOrderByFechaReservaDesc();
}