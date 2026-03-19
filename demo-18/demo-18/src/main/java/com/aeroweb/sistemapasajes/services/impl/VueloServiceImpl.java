package com.aeroweb.sistemapasajes.services.impl;

import com.aeroweb.sistemapasajes.dto.VueloDTO;
import com.aeroweb.sistemapasajes.entities.Aeropuerto;
import com.aeroweb.sistemapasajes.entities.Avion;
import com.aeroweb.sistemapasajes.entities.Vuelo;
import com.aeroweb.sistemapasajes.mappers.VueloMapper;
import com.aeroweb.sistemapasajes.repositories.AeropuertoRepository;
import com.aeroweb.sistemapasajes.repositories.AvionRepository;
import com.aeroweb.sistemapasajes.repositories.VueloRepository;
import com.aeroweb.sistemapasajes.services.VueloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired private VueloRepository vueloRepo;
    @Autowired private AvionRepository avionRepo;
    @Autowired private AeropuertoRepository aeroRepo;

    @Override
    @Transactional
    public VueloDTO guardarDesdeDTO(VueloDTO dto) {

        Avion avion = avionRepo.findById(dto.getIdAvion())
                .orElseThrow(() -> new RuntimeException("Avión no encontrado"));

        Aeropuerto origen = aeroRepo.findById(dto.getIdAeropuertoOrigen())
                .orElseThrow(() -> new RuntimeException("Origen no encontrado"));

        Aeropuerto destino = aeroRepo.findById(dto.getIdAeropuertoDestino())
                .orElseThrow(() -> new RuntimeException("Destino no encontrado"));

        Vuelo vuelo = new Vuelo();
        vuelo.setAvion(avion);
        vuelo.setOrigen(origen);
        vuelo.setDestino(destino);
        vuelo.setPrecioBase(dto.getPrecioBase());
        vuelo.setAsientosDisponibles(avion.getCapacidadTotal());
        vuelo.setEstado(Vuelo.EstadoVuelo.PROGRAMADO);

        vuelo.setFechaSalida(LocalDateTime.parse(dto.getFechaSalida()));

        // actualizar ubicación del avión
        avion.setAeropuertoActual(destino);
        avionRepo.save(avion);

        Vuelo guardado = vueloRepo.save(vuelo);

        // 🔥 USAR MAPPER CORRECTO
        return VueloMapper.toDTO(guardado);
    }

    @Override
    public List<VueloDTO> listarTodos() {
        return vueloRepo.findAll().stream()
                .map(VueloMapper::toDTO) // 🔥 CLAVE
                .collect(Collectors.toList());
    }

    @Override
    public List<VueloDTO> buscarVuelos(Integer origenId, Integer destinoId) {
        return vueloRepo.findByOrigen_IdAeropuertoAndDestino_IdAeropuerto(origenId, destinoId)
                .stream()
                .map(VueloMapper::toDTO) // 🔥 CLAVE
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        vueloRepo.deleteById(id);
    }
}