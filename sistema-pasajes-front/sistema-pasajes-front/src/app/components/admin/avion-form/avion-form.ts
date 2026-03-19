import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AvionService } from '../../../services/avion';
import { AeropuertoService } from '../../../services/aeropuerto';

@Component({
  selector: 'app-avion-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './avion-form.html'
})
export class AvionFormComponent implements OnInit {
  
  avion: any = { 
    idAvion: null,
    modelo: '', 
    capacidadTotal: null, 
    estado: 'ACTIVO',
    idAeropuertoActual: null 
  };

  aeropuertos: any[] = [];
  editMode = false;

  constructor(
    private avionService: AvionService,
    private aeroService: AeropuertoService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef 
  ) {}

  ngOnInit() {
    this.aeroService.listarTodos().subscribe(data => {
      this.aeropuertos = data; // Aquí vienen con "id" según tu captura
      this.cdr.detectChanges();
    });

    const id = this.route.snapshot.params['id'];
    if (id) {
      this.editMode = true;
      this.avionService.buscarPorId(id).subscribe(encontrado => {
        this.avion.idAvion = encontrado.idAvion;
        this.avion.modelo = encontrado.modelo;
        this.avion.capacidadTotal = encontrado.capacidadTotal;
        this.avion.estado = encontrado.estado;
        
        // Ajuste: El servidor devuelve aeropuertoActual.idAeropuerto o aeropuertoActual.id?
        // Si tu entidad Avion tiene aeropuertoActual, mapeamos su ID:
        if (encontrado.aeropuertoActual) {
          this.avion.idAeropuertoActual = encontrado.aeropuertoActual.id || encontrado.aeropuertoActual.idAeropuerto;
        }
        this.cdr.detectChanges();
      });
    }
  }

  onSelectionChange() {
    this.cdr.detectChanges();
  }

  guardar() {
    const payload = {
      idAvion: this.avion.idAvion,
      modelo: this.avion.modelo,
      capacidadTotal: Number(this.avion.capacidadTotal),
      estado: this.avion.estado,
      idAeropuertoActual: Number(this.avion.idAeropuertoActual)
    };

    const operacion = this.editMode 
      ? this.avionService.actualizar(this.avion.idAvion, payload) 
      : this.avionService.registrar(payload);

    operacion.subscribe({
      next: () => {
        alert('✅ ¡Avión guardado con éxito!');
        this.router.navigate(['/admin/aviones']);
      },
      error: (err) => alert('❌ Error: ' + (err.error?.message || 'Error en el servidor'))
    });
  }

  cancelar() { this.router.navigate(['/admin/aviones']); }
}