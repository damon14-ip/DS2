import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

import { AeropuertoService } from '../../../services/aeropuerto';

@Component({
  selector: 'app-crear-aeropuerto',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './crear-aeropuerto.html',
  styleUrls: ['./crear-aeropuerto.css']
})
export class CrearAeropuertoComponent {

  aeropuerto = {
    nombre: '',
    ciudad: '',
    pais: ''
  };

  cargando = false;

  constructor(private aeropuertoService: AeropuertoService) {}

  guardar(form: NgForm) {

    if (form.invalid) {
      Swal.fire("Error", "Complete todos los campos", "error");
      return;
    }

    this.cargando = true;

    this.aeropuertoService.crear(this.aeropuerto).subscribe({

      next: () => {

        Swal.fire({
          icon: "success",
          title: "Aeropuerto registrado",
          timer: 1500,
          showConfirmButton: false
        });

        form.resetForm();
        this.cargando = false;
      },

      error: () => {

        Swal.fire("Error", "No se pudo registrar el aeropuerto", "error");
        this.cargando = false;
      }

    });

  }

}