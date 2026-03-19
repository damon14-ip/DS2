package com.aeroweb.sistemapasajes.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vuelo")
public class Vuelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVuelo;

    @ManyToOne
    @JoinColumn(name = "id_avion")
    private Avion avion;

    @ManyToOne
    @JoinColumn(name = "id_aeropuerto_origen")
    private Aeropuerto origen;

    @ManyToOne
    @JoinColumn(name = "id_aeropuerto_destino")
    private Aeropuerto destino;

    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private BigDecimal precioBase;
    private Integer asientosDisponibles;

    @Enumerated(EnumType.STRING)
    private EstadoVuelo estado;

    public enum EstadoVuelo { PROGRAMADO, EN_VUELO, RETRASADO, FINALIZADO, CANCELADO }

    public Vuelo() {}

    public Integer getIdVuelo() { return idVuelo; }
    public void setIdVuelo(Integer idVuelo) { this.idVuelo = idVuelo; }
    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }
    public Aeropuerto getOrigen() { return origen; }
    public void setOrigen(Aeropuerto origen) { this.origen = origen; }
    public Aeropuerto getDestino() { return destino; }
    public void setDestino(Aeropuerto destino) { this.destino = destino; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }
    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }
    public BigDecimal getPrecioBase() { return precioBase; }
    public void setPrecioBase(BigDecimal precioBase) { this.precioBase = precioBase; }
    public Integer getAsientosDisponibles() { return asientosDisponibles; }
    public void setAsientosDisponibles(Integer asientosDisponibles) { this.asientosDisponibles = asientosDisponibles; }
    public EstadoVuelo getEstado() { return estado; }
    public void setEstado(EstadoVuelo estado) { this.estado = estado; }
}