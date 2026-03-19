import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AvionService } from '../../../services/avion'; // Ajusta la ruta si es necesario
import { Router } from '@angular/router';

@Component({
  selector: 'app-avion-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './avion-list.html'
})
export class AvionListComponent implements OnInit {
  listaAviones: any[] = [];

  constructor(
    private avionService: AvionService,
    private router: Router,
    private cdr: ChangeDetectorRef // Para el detectChanges
  ) {}

  ngOnInit(): void {
    this.cargarAviones();
  }

  cargarAviones(): void {
    this.avionService.listarTodos().subscribe({
      next: (data) => {
        this.listaAviones = data;
        this.cdr.detectChanges(); // Forzamos refresco de la tabla
      },
      error: (err) => console.error('Error al cargar', err)
    });
  }

  // ESTE MÉTODO FALTABA
  abrirModalCrear() {
    this.router.navigate(['/admin/aviones/nuevo']);
  }

  // ESTE MÉTODO FALTABA
  editar(avion: any) {
    this.router.navigate(['/admin/aviones/editar', avion.idAvion]);
  }

  borrar(id: number): void {
    if (confirm('¿Estás seguro de eliminar este avión?')) {
      this.avionService.eliminar(id).subscribe({
        next: () => {
          this.listaAviones = this.listaAviones.filter(a => a.idAvion !== id);
          this.cdr.detectChanges(); // Refresco inmediato al borrar
          alert('Avión eliminado correctamente');
        },
        error: (err) => alert('No se puede eliminar: el avión está en uso')
      });
    }
  }
}