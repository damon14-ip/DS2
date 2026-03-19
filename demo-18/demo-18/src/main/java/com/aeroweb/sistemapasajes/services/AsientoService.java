package com.aeroweb.sistemapasajes.services;

import com.aeroweb.sistemapasajes.entities.Asiento;

import java.util.List;

public interface AsientoService {

    List<Asiento> listarPorAvion(Integer avionId);

    List<Asiento> listarDisponibles(Integer avionId);

    Asiento crear(Asiento asiento);

    void ocuparAsiento(Integer id);

    void liberarAsiento(Integer id);

    Asiento buscarPorCodigo(Integer avionId, String codigo);
}
