import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VueloService } from '../../services/vuelo';
import { ReservaService } from '../../services/reserva';

@Component({
  selector: 'app-venta-pasaje',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './venta-pasaje.html'
})
export class VentaPasajeComponent implements OnInit {
  vuelos: any[] = [];
  idVueloSeleccionado: number | null = null;
  pasajero: any = { nombre: '', apellido: '', documento: '', edad: 18, asiento: '', tipoAsiento: 'TURISTA', asientoAleatorio: true };

  constructor(private vueloService: VueloService, private reservaService: ReservaService) {}

  ngOnInit() {
    this.vueloService.listarTodos().subscribe({
      next: (data) => this.vuelos = data,
      error: (err) => console.error('Error al cargar vuelos', err)
    });
  }

  procesarVenta() {
    if (!this.idVueloSeleccionado) return;
    const payload = { idVuelo: this.idVueloSeleccionado, pasajeros: [this.pasajero] };

    this.reservaService.crearReservaYDescargarPdf(payload).subscribe({
      next: (blob: Blob) => this.descargarTicket(blob, this.pasajero.documento),
      error: async (err) => alert('❌ Error: ' + await err.error.text())
    });
  }

  private descargarTicket(blob: Blob, doc: string) {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `Ticket_${doc}.pdf`;
    a.click();
    window.URL.revokeObjectURL(url);
    alert('✅ Venta Exitosa.');
    this.limpiarFormulario();
  }

  limpiarFormulario() {
    this.idVueloSeleccionado = null;
    this.pasajero = { nombre: '', apellido: '', documento: '', edad: 18, asiento: '', tipoAsiento: 'TURISTA', asientoAleatorio: true };
  }

  getPrecioSeleccionado() {
    const v = this.vuelos.find(x => x.idVuelo === this.idVueloSeleccionado);
    if (!v) return 0;
    return this.pasajero.tipoAsiento === 'VIP' ? v.precioBase * 1.30 : v.precioBase;
  }
}