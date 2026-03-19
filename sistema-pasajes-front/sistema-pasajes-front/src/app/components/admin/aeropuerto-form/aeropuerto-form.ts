import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AeropuertoService } from '../../../services/aeropuerto';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-aeropuerto-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './aeropuerto-form.html'
})
export class AeropuertoFormComponent implements OnInit {
  aero: any = { nombre: '', ciudad: '', codigoIata: '' };
  editMode = false;

  constructor(
    private service: AeropuertoService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.editMode = true;
      this.service.listarTodos().subscribe(res => {
        const found = res.find(a => a.id == id);
        if (found) this.aero = { ...found };
      });
    }
  }
cancelar() {
  this.router.navigate(['/admin/aeropuertos']);
}
  guardar() {
    const obs = this.editMode 
      ? this.service.actualizar(this.aero.id, this.aero) 
      : this.service.registrar(this.aero);

    obs.subscribe({
      next: () => this.router.navigate(['/admin/aeropuertos']),
      error: () => alert('Error al procesar')
    });
  }
}