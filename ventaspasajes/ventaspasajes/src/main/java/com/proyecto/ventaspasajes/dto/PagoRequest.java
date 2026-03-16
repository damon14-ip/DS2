package com.proyecto.ventaspasajes.dto;

public class PagoRequest {

    private Integer idPasaje;
    private String metodo;

    public PagoRequest() {}

    public Integer getIdPasaje() {
        return idPasaje;
    }

    public void setIdPasaje(Integer idPasaje) {
        this.idPasaje = idPasaje;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
}
