package com.aeroweb.sistemapasajes.services.impl;

import com.aeroweb.sistemapasajes.dto.AvionDTO;
import com.aeroweb.sistemapasajes.entities.Avion;
import com.aeroweb.sistemapasajes.entities.Aeropuerto;
import com.aeroweb.sistemapasajes.repositories.AvionRepository;
import com.aeroweb.sistemapasajes.repositories.AeropuertoRepository;
import com.aeroweb.sistemapasajes.services.AvionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AvionServiceImpl implements AvionService {

    @Autowired private AvionRepository avionRepository;
    @Autowired private AeropuertoRepository aeropuertoRepository;

    @Override
    public List<Avion> listarTodos() { 
        return avionRepository.findAll(); 
    }

    @Override
    @Transactional
    public Avion guardar(AvionDTO dto) {
        return mapearYGuardar(new Avion(), dto);
    }

    @Override
    @Transactional
    public Avion actualizar(Integer id, AvionDTO dto) {
        Avion existente = buscarPorId(id);
        return mapearYGuardar(existente, dto);
    }

    private Avion mapearYGuardar(Avion avion, AvionDTO dto) {
        avion.setModelo(dto.getModelo());
        avion.setCapacidadTotal(dto.getCapacidadTotal());
        avion.setEstado(dto.getEstado());

        if (dto.getIdAeropuertoActual() != null) {
            // Buscamos el aeropuerto por ID para establecer la relación
            Aeropuerto aero = aeropuertoRepository.findById(dto.getIdAeropuertoActual())
                .orElseThrow(() -> new RuntimeException("Aeropuerto no encontrado ID: " + dto.getIdAeropuertoActual()));
            avion.setAeropuertoActual(aero);
        }
        return avionRepository.save(avion);
    }

    @Override
    public Avion buscarPorId(Integer id) { 
        return avionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avión no encontrado")); 
    }

    @Override
    public List<Avion> listarPorAeropuertoYEstado(Integer idAeropuerto, Avion.EstadoAvion estado) {
        // Debe coincidir con el nombre del método en el repositorio
        return avionRepository.findByAeropuertoActual_IdAeropuertoAndEstado(idAeropuerto, estado);
    }

    @Override
    public void eliminar(Integer id) { 
        avionRepository.deleteById(id); 
    }
}