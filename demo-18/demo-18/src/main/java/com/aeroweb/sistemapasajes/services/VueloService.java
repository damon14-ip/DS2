package com.aeroweb.sistemapasajes.services;

import com.aeroweb.sistemapasajes.dto.VueloDTO;
import com.aeroweb.sistemapasajes.entities.Vuelo;
import java.util.List;

public interface VueloService {
    List<VueloDTO> buscarVuelos(Integer origenId, Integer destinoId);
    List<VueloDTO> listarTodos(); 
    VueloDTO guardarDesdeDTO(VueloDTO dto); // Cambiamos este
    void eliminar(Integer id);
}