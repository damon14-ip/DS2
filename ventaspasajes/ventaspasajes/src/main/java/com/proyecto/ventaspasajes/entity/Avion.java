package com.proyecto.ventaspasajes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "avion")
public class Avion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avion")
    private Integer idAvion;

    private String modelo;

    @Column(name = "capacidad_total")
    private Integer capacidadTotal;

    @Enumerated(EnumType.STRING)
    private EstadoAvion estado;

    public enum EstadoAvion{
        EN_ESPERA,
        EN_VUELO,
        MANTENIMIENTO
    }

    public Avion(){}

    public Integer getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Integer idAvion) {
        this.idAvion = idAvion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(Integer capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

    public EstadoAvion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAvion estado) {
        this.estado = estado;
    }

	public void setEstado(String string) {
		// TODO Auto-generated method stub
		
	}
}
