package com.proyecto.ventaspasajes.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="checkin")
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_checkin")
    private Integer idCheckin;

    @OneToOne
    @JoinColumn(name="id_detalle")
    private DetalleReserva detalleReserva;

    @Column(name="fecha_checkin")
    private LocalDateTime fechaCheckin;

    @Column(name="tarjeta_embarque")
    private String tarjetaEmbarque;

    @Enumerated(EnumType.STRING)
    private EstadoCheckin estado;

    public enum EstadoCheckin{
        PENDIENTE,
        REALIZADO
    }

    public Checkin(){}

    public Integer getIdCheckin() {
        return idCheckin;
    }

    public void setIdCheckin(Integer idCheckin) {
        this.idCheckin = idCheckin;
    }

    public DetalleReserva getDetalleReserva() {
        return detalleReserva;
    }

    public void setDetalleReserva(DetalleReserva detalleReserva) {
        this.detalleReserva = detalleReserva;
    }

    public LocalDateTime getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(LocalDateTime fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public String getTarjetaEmbarque() {
        return tarjetaEmbarque;
    }

    public void setTarjetaEmbarque(String tarjetaEmbarque) {
        this.tarjetaEmbarque = tarjetaEmbarque;
    }

    public EstadoCheckin getEstado() {
        return estado;
    }

    public void setEstado(EstadoCheckin estado) {
        this.estado = estado;
    }

	public void setEstado(String string) {
		// TODO Auto-generated method stub
		
	}
}
