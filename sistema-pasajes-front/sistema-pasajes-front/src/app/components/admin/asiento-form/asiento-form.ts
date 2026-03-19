import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AsientoService } from '../../../services/asiento';
import { AvionService } from '../../../services/avion';

@Component({
  selector: 'app-asiento-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './asiento-form.html',
  styleUrls: ['./asiento-form.css']
})
export class AsientoFormComponent implements OnInit {

  avionId: number | null = null;

  // 🔥 FORM CREAR
  nuevoAsiento: any = {
    codigoAsiento: '',
    clase: 'ECONOMICA',
    cargoAdicional: 0,
    avion: { idAvion: null }
  };

  aviones: any[] = [];
  asientos: any[] = [];

  total = 0;
  ocupados = 0;

  constructor(
    private asientoService: AsientoService,
    private avionService: AvionService
  ) {}

  ngOnInit() {
    this.avionService.listarTodos().subscribe(data => {
      this.aviones = data;
    });
  }

  // 🔍 BUSCAR
  buscar() {
    if (!this.avionId) return;

    this.asientoService.listarPorAvion(this.avionId)
      .subscribe(data => {
        this.asientos = data;
        this.total = data.length;
        this.ocupados = data.filter(a => a.ocupado).length;
      });

    this.nuevoAsiento.avion.idAvion = this.avionId;
  }

  // 💾 CREAR
  crear() {
    if (!this.nuevoAsiento.codigoAsiento) {
      alert('Código requerido');
      return;
    }

    this.asientoService.crear(this.nuevoAsiento).subscribe({
      next: () => {
        alert('✅ Asiento creado');
        this.nuevoAsiento.codigoAsiento = '';
        this.buscar();
      },
      error: (err) => alert(err.error?.message || 'Error')
    });
  }

  // 🪑 OCUPAR
  ocupar(asiento: any) {
    if (asiento.ocupado) return;

    this.asientoService.ocupar(asiento.idAsiento).subscribe(() => {
      asiento.ocupado = true;
      this.ocupados++;
    });
  }

  // 📊 FILAS
  filas() {
    const mapa: any = {};

    this.asientos.forEach(a => {
      const fila = a.codigoAsiento.match(/\d+/)[0];
      if (!mapa[fila]) mapa[fila] = [];
      mapa[fila].push(a);
    });

    return Object.keys(mapa)
      .sort((a,b)=>+a-+b)
      .map(f => ({
        numero: f,
        asientos: mapa[f].sort((a:any,b:any)=>
          a.codigoAsiento.localeCompare(b.codigoAsiento))
      }));
  }

  porcentaje(): number {
    if (this.total === 0) return 0;
    return Math.round((this.ocupados / this.total) * 100);
  }
}
