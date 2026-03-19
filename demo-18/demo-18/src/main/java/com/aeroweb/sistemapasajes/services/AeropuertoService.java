package com.aeroweb.sistemapasajes.services;

import com.aeroweb.sistemapasajes.dto.AeropuertoDTO;
import java.util.List;

public interface AeropuertoService {
    List<AeropuertoDTO> listarTodos();
    AeropuertoDTO guardar(AeropuertoDTO dto);
    void eliminar(Integer id);
    // Cambiado para que coincida con el Controller y el Impl
    AeropuertoDTO actualizar(Integer id, AeropuertoDTO dto);
}