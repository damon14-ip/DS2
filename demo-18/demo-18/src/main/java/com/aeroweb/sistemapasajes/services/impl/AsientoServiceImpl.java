package com.aeroweb.sistemapasajes.services.impl;

import com.aeroweb.sistemapasajes.entities.Asiento;
import com.aeroweb.sistemapasajes.repositories.AsientoRepository;
import com.aeroweb.sistemapasajes.services.AsientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AsientoServiceImpl implements AsientoService {

    @Autowired
    private AsientoRepository repo;

    @Override
    public List<Asiento> listarPorAvion(Integer avionId) {
        return repo.findByAvionIdAvion(avionId);
    }

    @Override
    public List<Asiento> listarDisponibles(Integer avionId) {
        return repo.findByAvionIdAvionAndOcupadoFalse(avionId);
    }

    @Override
    public Asiento crear(Asiento asiento) {

        if (asiento.getAvion() == null || asiento.getAvion().getIdAvion() == null) {
            throw new RuntimeException("Debe asignar un avión");
        }

        if (asiento.getCodigoAsiento() == null || asiento.getCodigoAsiento().trim().isEmpty()) {
            throw new RuntimeException("Código de asiento obligatorio");
        }

        String codigo = asiento.getCodigoAsiento().trim().toUpperCase();

        // Validar formato tipo A1, B2
        if (!codigo.matches("[A-Z][0-9]+")) {
            throw new RuntimeException("Formato inválido (Ej: A1, B2)");
        }

        if (repo.existsByAvionIdAvionAndCodigoAsiento(
                asiento.getAvion().getIdAvion(), codigo)) {
            throw new RuntimeException("El asiento ya existe en este avión");
        }

        asiento.setCodigoAsiento(codigo);
        asiento.setOcupado(false);

        if (asiento.getCargoAdicional() == null) {
            asiento.setCargoAdicional(BigDecimal.ZERO);
        }

        return repo.save(asiento);
    }

    @Override
    public void ocuparAsiento(Integer id) {
        Asiento asiento = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asiento no encontrado"));

        if (asiento.getOcupado()) {
            throw new RuntimeException("El asiento " + asiento.getCodigoAsiento() + " ya está ocupado");
        }

        asiento.setOcupado(true);
        repo.save(asiento);
    }

    @Override
    public Asiento buscarPorCodigo(Integer avionId, String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            throw new RuntimeException("Código de asiento obligatorio");
        }

        return repo.findByAvionIdAvionAndCodigoAsiento(avionId, codigo.trim().toUpperCase())
                .orElseThrow(() -> new RuntimeException("Asiento no encontrado"));
    }

	@Override
	public void liberarAsiento(Integer id) {
		// TODO Auto-generated method stub
		
	}


}
