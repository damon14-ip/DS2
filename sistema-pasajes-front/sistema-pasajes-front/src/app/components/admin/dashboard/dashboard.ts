import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { DashboardService } from '../../../services/dashboard'; // Usaremos el servicio centralizado

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class DashboardComponent implements OnInit {
  
  // Incluimos usuarios para que el panel esté completo
  stats = {
    aviones: 0,
    aeropuertos: 0,
    vuelos: 0,
    usuarios: 0
  };

  constructor(
    private router: Router,
    private dashService: DashboardService, // Inyectamos el nuevo servicio
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.cargarEstadisticas();
  }

  cargarEstadisticas() {
    this.dashService.getStats().subscribe({
      next: (data) => {
        // Mapeamos los datos del DTO de Java
        this.stats.aviones = data.totalAviones;
        this.stats.aeropuertos = data.totalAeropuertos;
        this.stats.vuelos = data.totalVuelos;
        this.stats.usuarios = data.totalUsuarios;
        
        // Forzamos la actualización de la vista
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error al cargar estadísticas:', err)
    });
  }

  navegar(ruta: string) {
    this.router.navigate([`/admin/${ruta}`]);
  }
}