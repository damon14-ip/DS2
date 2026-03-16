import { Component } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
import Swal from 'sweetalert2'

import { PasajeroService } from '../../../services/pasajero'

@Component({
  selector: 'app-crear-pasajero',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './crear-pasajero.html',
  styleUrls: ['./crear-pasajero.css']
})
export class CrearPasajeroComponent {

  pasajero:any = {
    nombre:'',
    apellido:'',
    documento:'',
    fechaNacimiento:''
  }

  constructor(private pasajeroService:PasajeroService){}

  guardar(){

    if(!this.pasajero.nombre ||
       !this.pasajero.apellido ||
       !this.pasajero.documento ||
       !this.pasajero.fechaNacimiento){

      Swal.fire(
        "Error",
        "Debe completar todos los datos",
        "error"
      )

      return
    }

    this.pasajeroService.registrar(this.pasajero).subscribe({

      next:()=>{

        Swal.fire(
          "Pasajero registrado",
          "El pasajero fue guardado correctamente",
          "success"
        )

        this.pasajero={
          nombre:'',
          apellido:'',
          documento:'',
          fechaNacimiento:''
        }

      },

      error:(err)=>{

        Swal.fire(
          "Error",
          err?.error?.message || "No se pudo registrar el pasajero",
          "error"
        )

      }

    })

  }

}