package com.aeroweb.sistemapasajes.services.impl;

import com.aeroweb.sistemapasajes.dto.AeropuertoDTO;
import com.aeroweb.sistemapasajes.entities.Aeropuerto;
import com.aeroweb.sistemapasajes.repositories.AeropuertoRepository;
import com.aeroweb.sistemapasajes.services.AeropuertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AeropuertoServiceImpl implements AeropuertoService {

    @Autowired 
    private AeropuertoRepository repo;

    @Override
    public List<AeropuertoDTO> listarTodos() {
        return repo.findAll().stream().map(a -> {
            AeropuertoDTO dto = new AeropuertoDTO();
            dto.setId(a.getIdAeropuerto());
            dto.setNombre(a.getNombre());
            dto.setCiudad(a.getCiudad());
            dto.setCodigoIata(a.getCodigoIata());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public AeropuertoDTO guardar(AeropuertoDTO dto) {
        Aeropuerto a = new Aeropuerto();
        a.setNombre(dto.getNombre());
        a.setCiudad(dto.getCiudad());
        a.setCodigoIata(dto.getCodigoIata());
        a = repo.save(a);
        dto.setId(a.getIdAeropuerto());
        return dto;
    }

    @Override
    public AeropuertoDTO actualizar(Integer id, AeropuertoDTO dto) {
        Aeropuerto a = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Aeropuerto no encontrado"));
        
        a.setNombre(dto.getNombre());
        a.setCiudad(dto.getCiudad());
        a.setCodigoIata(dto.getCodigoIata());
        
        repo.save(a);
        dto.setId(id);
        return dto;
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}