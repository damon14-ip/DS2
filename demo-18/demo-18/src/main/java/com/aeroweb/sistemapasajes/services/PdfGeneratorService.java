package com.aeroweb.sistemapasajes.services;

import com.aeroweb.sistemapasajes.entities.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGeneratorService {

    public byte[] generarTicketReserva(Reserva reserva) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            for (Pasajero p : reserva.getPasajeros()) {
                // Título y Logo Simulado
                Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
                Paragraph head = new Paragraph("BOARDING PASS - AEROWEB", boldFont);
                head.setAlignment(Element.ALIGN_CENTER);
                document.add(head);
                
                document.add(new Paragraph("PNR: " + reserva.getPnr() + " | Fecha Emisión: " + reserva.getFechaReserva().format(fmt)));
                document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));

                // Tabla de Datos
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingBefore(20f);

                // Fila 1: Pasajero
                table.addCell("PASAJERO:");
                table.addCell(p.getNombre() + " " + p.getApellido());

                // Fila 2: Origen y Destino
                table.addCell("DESDE:");
                table.addCell(reserva.getVuelo().getOrigen().getNombre());
                
                table.addCell("HACIA:");
                table.addCell(reserva.getVuelo().getDestino().getNombre());

                // Fila 3: Horarios
                table.addCell("SALIDA:");
                table.addCell(reserva.getVuelo().getFechaSalida().format(fmt));

                // Fila 4: Asiento (EL DATO CLAVE)
                Font asientoFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.NORMAL);
                table.addCell("ASIENTO:");
                table.addCell(new Phrase(p.getAsiento(), asientoFont));

                document.add(table);
                
                document.add(new Paragraph("\n\nNOTAS: Presentarse 2 horas antes para vuelos nacionales."));
                document.add(new Paragraph("Este ticket es personal e intransferible."));
                
                document.newPage(); // Una página por pasajero
            }

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el PDF", e);
        }
        return baos.toByteArray();
    }
}