import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AeropuertoService } from '../../../services/aeropuerto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-aeropuerto-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './aeropuerto-list.html'
})
export class AeropuertoListComponent implements OnInit {
  aeropuertos: any[] = [];

  constructor(
    private aeroService: AeropuertoService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargar();
  }

  cargar(): void {
    this.aeroService.listarTodos().subscribe(data => {
      this.aeropuertos = data;
      this.cdr.detectChanges();
    });
  }

  nuevo() { this.router.navigate(['/admin/aeropuertos/nuevo']); }

  editar(id: number) { this.router.navigate(['/admin/aeropuertos/editar', id]); }

  borrar(id: number) {
    if (confirm('¿Eliminar aeropuerto?')) {
      this.aeroService.eliminar(id).subscribe(() => {
        this.aeropuertos = this.aeropuertos.filter(a => a.id !== id);
        this.cdr.detectChanges();
      });
    }
  }
}