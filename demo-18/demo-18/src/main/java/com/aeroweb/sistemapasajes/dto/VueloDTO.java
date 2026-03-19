package com.aeroweb.sistemapasajes.dto;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VueloDTO {

    private Integer idVuelo;
    private Integer idAvion;

    private Integer idAeropuertoOrigen;
    private Integer idAeropuertoDestino;

    // ✅ NUEVOS CAMPOS (CLAVE)
    private String nombreOrigen;
    private String nombreDestino;
    private Integer asientosDisponibles;

    private String fechaSalida;
    private BigDecimal precioBase;

    // Getters y Setters
    public Integer getIdVuelo() { return idVuelo; }
    public void setIdVuelo(Integer idVuelo) { this.idVuelo = idVuelo; }

    public Integer getIdAvion() { return idAvion; }
    public void setIdAvion(Integer idAvion) { this.idAvion = idAvion; }

    public Integer getIdAeropuertoOrigen() { return idAeropuertoOrigen; }
    public void setIdAeropuertoOrigen(Integer idAeropuertoOrigen) { this.idAeropuertoOrigen = idAeropuertoOrigen; }

    public Integer getIdAeropuertoDestino() { return idAeropuertoDestino; }
    public void setIdAeropuertoDestino(Integer idAeropuertoDestino) { this.idAeropuertoDestino = idAeropuertoDestino; }

    public String getNombreOrigen() { return nombreOrigen; }
    public void setNombreOrigen(String nombreOrigen) { this.nombreOrigen = nombreOrigen; }

    public String getNombreDestino() { return nombreDestino; }
    public void setNombreDestino(String nombreDestino) { this.nombreDestino = nombreDestino; }

    public Integer getAsientosDisponibles() { return asientosDisponibles; }
    public void setAsientosDisponibles(Integer asientosDisponibles) { this.asientosDisponibles = asientosDisponibles; }

    public String getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(String fechaSalida) { this.fechaSalida = fechaSalida; }

    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }
}