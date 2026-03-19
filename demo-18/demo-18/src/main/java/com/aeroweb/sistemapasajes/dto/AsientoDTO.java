package com.aeroweb.sistemapasajes.dto;

import java.math.BigDecimal;

public class AsientoDTO {

    private Integer idAsiento;
    private Integer idAvion;
    private String codigoAsiento;
    private String clase;
    private BigDecimal cargoAdicional;
    private Boolean ocupado;

    // GETTERS Y SETTERS

    public Integer getIdAsiento() { return idAsiento; }
    public void setIdAsiento(Integer idAsiento) { this.idAsiento = idAsiento; }

    public Integer getIdAvion() { return idAvion; }
    public void setIdAvion(Integer idAvion) { this.idAvion = idAvion; }

    public String getCodigoAsiento() { return codigoAsiento; }
    public void setCodigoAsiento(String codigoAsiento) { this.codigoAsiento = codigoAsiento; }

    public String getClase() { return clase; }
    public void setClase(String clase) { this.clase = clase; }

    public BigDecimal getCargoAdicional() { return cargoAdicional; }
    public void setCargoAdicional(BigDecimal cargoAdicional) { this.cargoAdicional = cargoAdicional; }

    public Boolean getOcupado() { return ocupado; }
    public void setOcupado(Boolean ocupado) { this.ocupado = ocupado; }
}
