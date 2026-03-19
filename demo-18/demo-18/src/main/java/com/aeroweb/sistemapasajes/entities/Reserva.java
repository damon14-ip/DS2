package com.aeroweb.sistemapasajes.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne // NUEVO: Relación con el vuelo para el PDF
    @JoinColumn(name = "id_vuelo")
    private Vuelo vuelo;

    @Column(unique = true, length = 6)
    private String pnr;

    private LocalDateTime fechaReserva;
    private BigDecimal totalReserva;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<Pasajero> pasajeros = new ArrayList<>();

    public enum EstadoPago { PENDIENTE, PAGADO, CANCELADO, EXPIRADO }

    public Reserva() { this.fechaReserva = LocalDateTime.now(); }

    // Getters y Setters
    public Long getIdReserva() { return idReserva; }
    public void setIdReserva(Long idReserva) { this.idReserva = idReserva; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Vuelo getVuelo() { return vuelo; } // NUEVO
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; } // NUEVO
    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }
    public LocalDateTime getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDateTime fechaReserva) { this.fechaReserva = fechaReserva; }
    public BigDecimal getTotalReserva() { return totalReserva; }
    public void setTotalReserva(BigDecimal totalReserva) { this.totalReserva = totalReserva; }
    public EstadoPago getEstadoPago() { return estadoPago; }
    public void setEstadoPago(EstadoPago estadoPago) { this.estadoPago = estadoPago; }
    public List<Pasajero> getPasajeros() { return pasajeros; }
    public void setPasajeros(List<Pasajero> pasajeros) { this.pasajeros = pasajeros; }
}