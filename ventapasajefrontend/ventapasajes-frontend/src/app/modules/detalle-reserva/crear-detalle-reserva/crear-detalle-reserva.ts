import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DetalleReservaService } from '../../../services/detalle-reserva';

@Component({
  selector: 'app-crear-detalle-reserva',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './crear-detalle-reserva.html',
  styleUrls: ['./crear-detalle-reserva.css']
})
export class CrearDetalleReservaComponent {

  detalle:any={
    reserva:{idReserva:''},
    vuelo:{idVuelo:''},
    pasajero:{idPasajero:''},
    asiento:{idAsiento:''},
    tipoAsiento:'ALEATORIO'
  }

  constructor(private service:DetalleReservaService){}

  guardar(){

    this.service.agregarDetalle(this.detalle).subscribe({
      next:(data)=>{
        alert("Detalle agregado correctamente")
        console.log(data)
      },
      error:(err)=>{
        alert("Error al registrar")
        console.log(err)
      }
    })

  }

}
