package com.aeroweb.sistemapasajes.services;

import com.aeroweb.sistemapasajes.dto.AvionDTO;
import com.aeroweb.sistemapasajes.entities.Avion;
import java.util.List;

public interface AvionService {
    List<Avion> listarTodos();
    Avion guardar(AvionDTO dto);
    Avion actualizar(Integer id, AvionDTO dto);
    void eliminar(Integer id);
    Avion buscarPorId(Integer id);
    List<Avion> listarPorAeropuertoYEstado(Integer idAeropuerto, Avion.EstadoAvion estado);
}