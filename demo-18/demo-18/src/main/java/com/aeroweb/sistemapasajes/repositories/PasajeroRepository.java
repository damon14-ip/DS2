package com.aeroweb.sistemapasajes.repositories;

import com.aeroweb.sistemapasajes.entities.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {

    // Validar si el asiento ya está ocupado en ese vuelo
    boolean existsByReserva_Vuelo_IdVueloAndAsiento(Integer idVuelo, String asiento);

    // Validar que el pasajero no tenga otro vuelo al mismo tiempo
    boolean existsByNumDocumentoAndReserva_Vuelo_FechaSalidaBeforeAndReserva_Vuelo_FechaLlegadaAfter(
            String documento, LocalDateTime fechaLlegada, LocalDateTime fechaSalida);
}