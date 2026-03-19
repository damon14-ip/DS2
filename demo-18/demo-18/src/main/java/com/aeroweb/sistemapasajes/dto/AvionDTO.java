package com.aeroweb.sistemapasajes.dto;

import com.aeroweb.sistemapasajes.entities.Avion.EstadoAvion;

public class AvionDTO {
    private Integer idAvion;
    private String modelo;
    private Integer capacidadTotal;
    private EstadoAvion estado;
    private Integer idAeropuertoActual; // Recibimos el ID, no el objeto

    public AvionDTO() {}

    // Getters y Setters
    public Integer getIdAvion() { return idAvion; }
    public void setIdAvion(Integer idAvion) { this.idAvion = idAvion; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getCapacidadTotal() { return capacidadTotal; }
    public void setCapacidadTotal(Integer capacidadTotal) { this.capacidadTotal = capacidadTotal; }

    public EstadoAvion getEstado() { return estado; }
    public void setEstado(EstadoAvion estado) { this.estado = estado; }

    public Integer getIdAeropuertoActual() { return idAeropuertoActual; }
    public void setIdAeropuertoActual(Integer idAeropuertoActual) { this.idAeropuertoActual = idAeropuertoActual; }
}