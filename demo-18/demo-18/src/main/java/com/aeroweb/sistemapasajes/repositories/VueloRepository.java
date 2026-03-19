package com.aeroweb.sistemapasajes.repositories;

import com.aeroweb.sistemapasajes.entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VueloRepository extends JpaRepository<Vuelo, Integer> {

    // Buscar vuelos por origen y destino (IDs)
    List<Vuelo> findByOrigen_IdAeropuertoAndDestino_IdAeropuerto(Integer origenId, Integer destinoId);

    // Buscar el último vuelo de un avión para validar escala (Turnaround)
    // Nota: El guion bajo permite navegar Vuelo -> Avion -> idAvion
    Vuelo findFirstByAvion_IdAvionAndEstadoOrderByFechaLlegadaDesc(Integer idAvion, Vuelo.EstadoVuelo estado);
}