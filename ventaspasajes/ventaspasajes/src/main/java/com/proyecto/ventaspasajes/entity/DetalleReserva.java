package com.proyecto.ventaspasajes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_reserva")
public class DetalleReserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "id_pasajero")
    private Pasajero pasajero;

    @ManyToOne
    @JoinColumn(name = "id_asiento")
    private Asiento asiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_asiento")
    private TipoAsientoReserva tipoAsiento;

    @Column(name = "precio_final")
    private Double precioFinal;

    public enum TipoAsientoReserva{
        ALEATORIO,
        ESPECIFICO
    }

    public DetalleReserva(){}

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public Double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Double precioFinal) {
        this.precioFinal = precioFinal;
    }
}
