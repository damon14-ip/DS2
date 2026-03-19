package com.aeroweb.sistemapasajes.dto;

import java.util.List;

public class ReservaRequestDTO {
    private Integer idVuelo;
    private List<PasajeroDTO> pasajeros; // Lista de personas para esta reserva

    public ReservaRequestDTO() {}

    public Integer getIdVuelo() { return idVuelo; }
    public void setIdVuelo(Integer idVuelo) { this.idVuelo = idVuelo; }

    public List<PasajeroDTO> getPasajeros() { return pasajeros; }
    public void setPasajeros(List<PasajeroDTO> pasajeros) { this.pasajeros = pasajeros; }
}