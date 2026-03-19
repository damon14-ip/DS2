import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { VueloService } from '../../../services/vuelo';
import { AeropuertoService } from '../../../services/aeropuerto';
import { AvionService } from '../../../services/avion';

@Component({
  selector: 'app-vuelo-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './vuelo-form.html'
})
export class VueloFormComponent implements OnInit {
  
  vuelo: any = {
    idVuelo: null,
    idAvion: null,
    idAeropuertoOrigen: null,
    idAeropuertoDestino: null,
    precioBase: 0,
    fechaSalida: null
  };

  fechaCompleta: string = '';
  aeropuertos: any[] = [];
  aviones: any[] = [];
  editMode = false;

  constructor(
    private vueloService: VueloService,
    private aeroService: AeropuertoService,
    private avionService: AvionService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.cargarAeropuertos();
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.editMode = true;
      // Aquí cargarías los datos del vuelo si fuera edición
    }
  }

  cargarAeropuertos() {
    this.aeroService.listarTodos().subscribe(data => {
      this.aeropuertos = data;
      this.cdr.detectChanges();
    });
  }

  cargarAvionesPorOrigen() {
    const origenId = this.vuelo.idAeropuertoOrigen;
    console.log("Filtrando aviones para aeropuerto ID:", origenId);
    
    if (origenId) {
      this.avionService.listarPorAeropuerto(origenId).subscribe({
        next: (data) => {
          this.aviones = data;
          this.vuelo.idAvion = null; // Resetear avión al cambiar origen
          console.log("Aviones disponibles cargados:", this.aviones);
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error("Error al cargar aviones:", err);
          this.aviones = [];
        }
      });
    } else {
      this.aviones = [];
    }
  }

  guardar() {
    if (this.vuelo.idAeropuertoOrigen === this.vuelo.idAeropuertoDestino) {
      alert('⚠️ El destino no puede ser igual al origen.');
      return;
    }

    const payload = {
      ...this.vuelo,
      idAeropuertoOrigen: Number(this.vuelo.idAeropuertoOrigen),
      idAeropuertoDestino: Number(this.vuelo.idAeropuertoDestino),
      idAvion: Number(this.vuelo.idAvion),
      fechaSalida: this.fechaCompleta
    };

    this.vueloService.crearVuelo(payload).subscribe({
      next: () => {
        alert('✅ Vuelo programado con éxito');
        this.router.navigate(['/admin/vuelos']);
      },
      error: (err) => {
        const msg = err.error?.message || 'No se pudo crear el vuelo';
        alert('❌ Error: ' + msg);
      }
    });
  }

  cancelar() { this.router.navigate(['/admin/vuelos']); }
}