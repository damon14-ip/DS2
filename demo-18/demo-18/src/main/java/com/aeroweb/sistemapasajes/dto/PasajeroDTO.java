package com.aeroweb.sistemapasajes.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PasajeroDTO {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo debe contener letras")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El apellido solo debe contener letras")
    private String apellido;

    @NotBlank(message = "El documento es obligatorio")
    @Pattern(regexp = "^\\d{8,12}$", message = "El documento debe ser numérico y tener entre 8 y 12 dígitos")
    private String documento;

    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    private int edad;

    private LocalDate fechaNacimiento;
    private String categoria;
    private boolean asientoAleatorio; 
    private String tipoAsiento;


    public PasajeroDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public boolean isAsientoAleatorio() { return asientoAleatorio; }
    public void setAsientoAleatorio(boolean asientoAleatorio) { this.asientoAleatorio = asientoAleatorio; }
    public String getTipoAsiento() { return tipoAsiento; }
    public void setTipoAsiento(String tipoAsiento) { this.tipoAsiento = tipoAsiento; }
}