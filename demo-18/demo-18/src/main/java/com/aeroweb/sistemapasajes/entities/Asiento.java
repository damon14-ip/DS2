package com.aeroweb.sistemapasajes.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "asiento",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_avion", "codigo_asiento"}))
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avion", nullable = false)
    private Avion avion;

    @Column(name = "codigo_asiento", nullable = false, length = 10)
    private String codigoAsiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaseAsiento clase;

    @Column(nullable = false)
    private BigDecimal cargoAdicional = BigDecimal.ZERO;

    @Column(nullable = false)
    private Boolean ocupado = false;

    // 🔥 Control de concurrencia (evita doble compra)
    @Version
    private Integer version;

    // 🎫 Clases de asiento
    public enum ClaseAsiento {
        ECONOMICA, EJECUTIVA, PRIMERA_CLASE
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    public Integer getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Integer idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public String getCodigoAsiento() {
        return codigoAsiento;
    }

    public void setCodigoAsiento(String codigoAsiento) {
        this.codigoAsiento = codigoAsiento != null ? codigoAsiento.trim().toUpperCase() : null;
    }

    public ClaseAsiento getClase() {
        return clase;
    }

    public void setClase(ClaseAsiento clase) {
        this.clase = clase;
    }

    public BigDecimal getCargoAdicional() {
        return cargoAdicional;
    }

    public void setCargoAdicional(BigDecimal cargoAdicional) {
        this.cargoAdicional = (cargoAdicional != null) ? cargoAdicional : BigDecimal.ZERO;
    }

    public Boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
