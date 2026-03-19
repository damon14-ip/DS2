package com.aeroweb.sistemapasajes.mappers;

import com.aeroweb.sistemapasajes.dto.AsientoDTO;
import com.aeroweb.sistemapasajes.entities.Asiento;
import com.aeroweb.sistemapasajes.entities.Avion;

public class AsientoMapper {

    // 🔁 ENTITY → DTO
    public static AsientoDTO toDTO(Asiento asiento) {
        AsientoDTO dto = new AsientoDTO();

        dto.setIdAsiento(asiento.getIdAsiento());
        dto.setIdAvion(asiento.getAvion().getIdAvion());
        dto.setCodigoAsiento(asiento.getCodigoAsiento());
        dto.setClase(asiento.getClase().name());
        dto.setCargoAdicional(asiento.getCargoAdicional());
        dto.setOcupado(asiento.getOcupado());

        return dto;
    }

    // 🔁 DTO → ENTITY
    public static Asiento toEntity(AsientoDTO dto) {
        Asiento asiento = new Asiento();

        asiento.setIdAsiento(dto.getIdAsiento());

        Avion avion = new Avion();
        avion.setIdAvion(dto.getIdAvion());
        asiento.setAvion(avion);

        asiento.setCodigoAsiento(dto.getCodigoAsiento());
        asiento.setClase(Asiento.ClaseAsiento.valueOf(dto.getClase()));
        asiento.setCargoAdicional(dto.getCargoAdicional());
        asiento.setOcupado(dto.getOcupado());

        return asiento;
    }
}
