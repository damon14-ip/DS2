package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Aeropuerto;
import com.proyecto.ventaspasajes.repository.AeropuertoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeropuertoService {

    private final AeropuertoRepository aeropuertoRepository;

    public AeropuertoService(AeropuertoRepository aeropuertoRepository) {
        this.aeropuertoRepository = aeropuertoRepository;
    }

    public List<Aeropuerto> listar() {
        return aeropuertoRepository.findAll();
    }

    public Aeropuerto buscarPorId(Integer id) {
        return aeropuertoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aeropuerto no encontrado"));
    }

    public Aeropuerto guardar(Aeropuerto aeropuerto) {
        return aeropuertoRepository.save(aeropuerto);
    }

    public Aeropuerto actualizar(Integer id, Aeropuerto aeropuerto) {
        Aeropuerto existente = buscarPorId(id);

        existente.setNombre(aeropuerto.getNombre());
        existente.setCiudad(aeropuerto.getCiudad());
        existente.setPais(aeropuerto.getPais());

        return aeropuertoRepository.save(existente);
    }

    public void eliminar(Integer id) {
        aeropuertoRepository.deleteById(id);
    }
}