package com.aeroweb.sistemapasajes.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pasajero")
public class Pasajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasajero;

    private String nombre;
    private String apellido;
    private String numDocumento;

    // ✅ LO MANTENEMOS para compatibilidad (PDF / frontend)
    private String asiento;

    private LocalDate fechaNacimiento;

    // 🔥 NUEVA RELACIÓN REAL (sin romper lo anterior)
    @ManyToOne
    @JoinColumn(name = "id_asiento")
    private Asiento asientoRef;

    @ManyToOne
    @JoinColumn(name = "id_reserva")
    private Reserva reserva;

    public Pasajero() {}

    // ===== GETTERS Y SETTERS =====

    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }

    public Asiento getAsientoRef() { return asientoRef; }
    public void setAsientoRef(Asiento asientoRef) { 
        this.asientoRef = asientoRef;

        // 🔥 sincroniza automáticamente el código
        if (asientoRef != null) {
            this.asiento = asientoRef.getCodigoAsiento();
        }
    }

    public Reserva getReserva() { return reserva; }
    public void setReserva(Reserva reserva) { this.reserva = reserva; }

    public Long getIdPasajero() { return idPasajero; }
    public void setIdPasajero(Long idPasajero) { this.idPasajero = idPasajero; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getNumDocumento() { return numDocumento; }
    public void setNumDocumento(String numDocumento) { this.numDocumento = numDocumento; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}