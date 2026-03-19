package com.aeroweb.sistemapasajes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "avion")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvion;
    
    private String modelo;
    private Integer capacidadTotal;
    
    @Enumerated(EnumType.STRING)
    private EstadoAvion estado;

    @ManyToOne
    @JoinColumn(name = "aeropuerto_actual_id")
    private Aeropuerto aeropuertoActual; // Este es el campo que causa el error

    @Version
    private Integer version;

    public enum EstadoAvion { ACTIVO, MANTENIMIENTO, INACTIVO }

    public Avion() {}

    // --- GETTERS Y SETTERS QUE FALTABAN ---
    
    public Aeropuerto getAeropuertoActual() {
        return aeropuertoActual;
    }

    public void setAeropuertoActual(Aeropuerto aeropuertoActual) {
        this.aeropuertoActual = aeropuertoActual;
    }

    // --- OTROS GETTERS Y SETTERS ---

    public Integer getIdAvion() { return idAvion; }
    public void setIdAvion(Integer idAvion) { this.idAvion = idAvion; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Integer getCapacidadTotal() { return capacidadTotal; }
    public void setCapacidadTotal(Integer capacidadTotal) { this.capacidadTotal = capacidadTotal; }
    public EstadoAvion getEstado() { return estado; }
    public void setEstado(EstadoAvion estado) { this.estado = estado; }
}