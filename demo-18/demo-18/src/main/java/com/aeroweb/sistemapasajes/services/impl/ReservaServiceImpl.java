package com.aeroweb.sistemapasajes.services.impl;

import com.aeroweb.sistemapasajes.dto.*;
import com.aeroweb.sistemapasajes.entities.*;
import com.aeroweb.sistemapasajes.repositories.*;
import com.aeroweb.sistemapasajes.services.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired private ReservaRepository reservaRepo;
    @Autowired private VueloRepository vueloRepo;
    @Autowired private UsuarioRepository usuarioRepo;
    @Autowired private PasajeroRepository pasajeroRepo;

    @Override
    @Transactional
    public ReservaRespuestaDTO procesarReserva(ReservaRequestDTO request, Long usuarioId) {
        
        Vuelo vueloActual = vueloRepo.findById(request.getIdVuelo())
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado"));

        // 1. VALIDACIÓN: CIERRE DE VENTAS
        if (LocalDateTime.now().isAfter(vueloActual.getFechaSalida().minusHours(3))) {
            throw new RuntimeException("Venta cerrada. Solo se permite reservar hasta 3 horas antes.");
        }

        // 2. VALIDACIÓN LOGÍSTICA: TIEMPO DE ESCALA
        Vuelo ultimoVueloAvion = vueloRepo.findFirstByAvion_IdAvionAndEstadoOrderByFechaLlegadaDesc(
                vueloActual.getAvion().getIdAvion(), Vuelo.EstadoVuelo.FINALIZADO);

        if (ultimoVueloAvion != null && ultimoVueloAvion.getFechaLlegada() != null) {
            long minutosEntreVuelos = Duration.between(ultimoVueloAvion.getFechaLlegada(), vueloActual.getFechaSalida()).toMinutes();
            if (minutosEntreVuelos < 45) {
                throw new RuntimeException("El avión requiere 45 min de preparación.");
            }
            if (!ultimoVueloAvion.getDestino().getIdAeropuerto().equals(vueloActual.getOrigen().getIdAeropuerto())) {
                throw new RuntimeException("El avión no está en el aeropuerto de origen.");
            }
        }

        // 3. VALIDACIÓN DE PASAJEROS
        long adultos = request.getPasajeros().stream().filter(p -> p.getEdad() >= 18).count();
        long infantes = request.getPasajeros().stream().filter(p -> p.getEdad() < 2).count();

        if (adultos == 0) throw new RuntimeException("Debe haber al menos un adulto.");
        if (infantes > adultos) throw new RuntimeException("Máximo un infante por cada adulto.");

        // 4. CREAR LA RESERVA
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setVuelo(vueloActual);
        reserva.setPnr(generarPNR());
        reserva.setFechaReserva(LocalDateTime.now());
        reserva.setEstadoPago(Reserva.EstadoPago.PAGADO);
        
        final Reserva reservaPersistida = reservaRepo.save(reserva);

        BigDecimal totalReserva = BigDecimal.ZERO;
        Set<String> documentosEnRequest = new HashSet<>();

        for (PasajeroDTO pDto : request.getPasajeros()) {
            // 5. VALIDACIÓN: SOLAPAMIENTO
            boolean tieneVueloCruzado = pasajeroRepo.existsByNumDocumentoAndReserva_Vuelo_FechaSalidaBeforeAndReserva_Vuelo_FechaLlegadaAfter(
                    pDto.getDocumento(), vueloActual.getFechaLlegada(), vueloActual.getFechaSalida());
            
            if (tieneVueloCruzado) throw new RuntimeException("Pasajero " + pDto.getDocumento() + " ya tiene un vuelo en este horario.");
            if (!documentosEnRequest.add(pDto.getDocumento())) throw new RuntimeException("Documento duplicado: " + pDto.getDocumento());

            // Asiento y precio
            String asientoFinal = pDto.isAsientoAleatorio() ? "A-" + (int)(Math.random()*100) : pDto.getTipoAsiento();
            if (pasajeroRepo.existsByReserva_Vuelo_IdVueloAndAsiento(vueloActual.getIdVuelo(), asientoFinal)) {
                throw new RuntimeException("Asiento " + asientoFinal + " ya ocupado.");
            }

            BigDecimal cargoExtra = calcularCargoAsiento(vueloActual.getPrecioBase(), pDto);
            totalReserva = totalReserva.add(aplicarDescuentoEdad(vueloActual.getPrecioBase().add(cargoExtra), pDto.getEdad()));

            Pasajero p = new Pasajero();
            p.setNombre(pDto.getNombre());
            p.setApellido(pDto.getApellido());
            p.setNumDocumento(pDto.getDocumento());
            p.setAsiento(asientoFinal);
            p.setReserva(reservaPersistida);
            pasajeroRepo.save(p);
        }

        // 6. ACTUALIZACIÓN FINAL
        reservaPersistida.setTotalReserva(totalReserva);
        vueloActual.setAsientosDisponibles(vueloActual.getAsientosDisponibles() - request.getPasajeros().size());
        
        reservaRepo.save(reservaPersistida);
        vueloRepo.save(vueloActual);

        return new ReservaRespuestaDTO(reservaPersistida.getPnr(), totalReserva, "Reserva exitosa", "OK");
    }

    private BigDecimal calcularCargoAsiento(BigDecimal precioBase, PasajeroDTO pDto) {
        if (pDto.isAsientoAleatorio()) return BigDecimal.ZERO;
        return "VIP".equalsIgnoreCase(pDto.getTipoAsiento()) ? precioBase.multiply(new BigDecimal("0.3")) : new BigDecimal("15");
    }

    private BigDecimal aplicarDescuentoEdad(BigDecimal precio, int edad) {
        if (edad < 2) return precio.multiply(new BigDecimal("0.1"));
        if (edad < 12) return precio.multiply(new BigDecimal("0.75"));
        return precio;
    }

    private String generarPNR() { return UUID.randomUUID().toString().substring(0, 6).toUpperCase(); }
}