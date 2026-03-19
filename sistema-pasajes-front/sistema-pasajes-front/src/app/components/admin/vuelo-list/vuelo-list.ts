import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VueloService } from '../../../services/vuelo';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vuelo-list',
  standalone: true,
  imports: [CommonModule],
  // Asegúrate de que el nombre sea EXACTO al del archivo en tu carpeta
  templateUrl: './vuelo-list.html' 
})
export class VueloListComponent implements OnInit {
  vuelos: any[] = [];

  constructor(
    private vueloService: VueloService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarVuelos();
  }

  cargarVuelos(): void {
    this.vueloService.listarTodos().subscribe({
      next: (data) => {
        this.vuelos = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Error al cargar vuelos', err)
    });
  }

  nuevoVuelo() {
    this.router.navigate(['/admin/vuelos/nuevo']);
  }

  borrar(id: number) {
    if (confirm('¿Desea eliminar este vuelo?')) {
      this.vueloService.eliminarVuelo(id).subscribe(() => {
        this.vuelos = this.vuelos.filter(v => v.id !== id);
        this.cdr.detectChanges();
      });
    }
  }
}