package com.aeroweb.sistemapasajes.dto;

import java.math.BigDecimal;

public class ReservaRespuestaDTO {
    private String pnr;
    private BigDecimal totalPagado;
    private String mensaje;
    private String estado;

    // Constructor vacío (necesario para frameworks como Jackson)
    public ReservaRespuestaDTO() {}

    // CONSTRUCTOR CORREGIDO (El que pide el servicio)
    public ReservaRespuestaDTO(String pnr, BigDecimal totalPagado, String mensaje, String estado) {
        this.pnr = pnr;
        this.totalPagado = totalPagado;
        this.mensaje = mensaje;
        this.estado = estado;
    }

    // Getters y Setters
    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public BigDecimal getTotalPagado() { return totalPagado; }
    public void setTotalPagado(BigDecimal totalPagado) { this.totalPagado = totalPagado; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}