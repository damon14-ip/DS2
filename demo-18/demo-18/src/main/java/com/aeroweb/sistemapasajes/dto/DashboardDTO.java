package com.aeroweb.sistemapasajes.dto;

public record DashboardDTO(
    long totalAviones,
    long totalVuelos,
    long totalUsuarios,
    long totalAeropuertos
) {}