 package com.proyecto.ventaspasajes.service;

import com.proyecto.ventaspasajes.entity.Asiento;
import com.proyecto.ventaspasajes.entity.Avion;
import com.proyecto.ventaspasajes.repository.AsientoRepository;
import com.proyecto.ventaspasajes.repository.AvionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsientoService {

    @Autowired
    private AsientoRepository asientoRepository;

    @Autowired
    private AvionRepository avionRepository;

    // Listar todos los asientos
    public List<Asiento> listar() {
        return asientoRepository.findAll();
    }

    // Listar asientos por avión
    public List<Asiento> listarPorAvion1(Integer idAvion) {
        return asientoRepository.findByAvionIdAvion(idAvion);
    }

    // Guardar asiento
    public Asiento guardar(Asiento asiento) {

        if (asiento.getAvion() == null) {
            throw new RuntimeException("Debe seleccionar un avión");
        }

        Avion avion = avionRepository.findById(
                asiento.getAvion().getIdAvion()
        ).orElseThrow(() ->
                new RuntimeException("Avión no encontrado")
        );

        long cantidadAsientos =
                asientoRepository.countByAvionIdAvion(avion.getIdAvion());

        if (cantidadAsientos >= avion.getCapacidadTotal()) {
            throw new RuntimeException("Se alcanzó la capacidad máxima del avión");
        }

        asiento.setAvion(avion);

        return asientoRepository.save(asiento);
    }

    // Eliminar asiento
    public void eliminar(Integer id) {

        if (!asientoRepository.existsById(id)) {
            throw new RuntimeException("El asiento no existe");
        }

        asientoRepository.deleteById(id);
    }

	public List<Asiento> listarPorAvion(Integer idAvion) {
		// TODO Auto-generated method stub
		return null;
	}
}