package com.aeroweb.sistemapasajes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "aeropuerto")
public class Aeropuerto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAeropuerto;
    
    private String nombre;
    
    @Column(unique = true, length = 3)
    private String codigoIata;
    
    private String ciudad;
    private String pais;
    

    public Aeropuerto() {}

    public Integer getIdAeropuerto() { return idAeropuerto; }
    public void setIdAeropuerto(Integer idAeropuerto) { this.idAeropuerto = idAeropuerto; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCodigoIata() { return codigoIata; }
    public void setCodigoIata(String codigoIata) { this.codigoIata = codigoIata; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}