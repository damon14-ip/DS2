import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { AsientoService } from '../../../services/asiento';
import { AvionService, Avion } from '../../../services/avion';

@Component({
  selector: 'app-crear-asiento',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './crear-asiento.html',
  styleUrls: ['./crear-asiento.css']
})
export class CrearAsientoComponent implements OnInit {

  aviones: Avion[] = [];

  asiento = {
    numero: '',
    tipo: 'COMERCIAL',
    precioExtra: 0,
    avion: {
      idAvion: null
    }
  };

  mensaje = '';

  constructor(
    private asientoService: AsientoService,
    private avionService: AvionService
  ) {}

  ngOnInit() {
    this.cargarAviones();
  }

  cargarAviones() {
    this.avionService.listar().subscribe(data => {
      this.aviones = data;
    });
  }

  guardar() {

    if (!this.asiento.numero || !this.asiento.avion.idAvion) {
      this.mensaje = "Completa todos los campos";
      return;
    }

    if (this.asiento.tipo === 'COMERCIAL') {
      this.asiento.precioExtra = 0;
    }

    this.asientoService.crear(this.asiento).subscribe({

      next: () => {
        this.mensaje = "✅ Asiento creado correctamente";
        this.reset();
      },

      error: () => {
        this.mensaje = "❌ Error al crear asiento";
      }

    });

  }

  reset() {
    this.asiento = {
      numero: '',
      tipo: 'COMERCIAL',
      precioExtra: 0,
      avion: { idAvion: null }
    };
  }

}