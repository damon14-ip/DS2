package com.proyecto.ventaspasajes.dto;

public class ComprarPasajeRequest {

    private Integer idUsuario;
    private Integer idVuelo;
    private Integer idAsiento;
    private Integer idEquipaje;

    public ComprarPasajeRequest() {}

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Integer getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Integer idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Integer getIdEquipaje() {
        return idEquipaje;
    }

    public void setIdEquipaje(Integer idEquipaje) {
        this.idEquipaje = idEquipaje;
    }
}
