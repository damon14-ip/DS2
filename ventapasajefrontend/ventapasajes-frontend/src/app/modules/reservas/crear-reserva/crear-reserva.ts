import { Component, OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
import Swal from 'sweetalert2'

import { ReservaService } from '../../../services/reserva'
import { VueloService } from '../../../services/vuelo'
import { PasajeroService } from '../../../services/pasajero'

@Component({
selector:'app-crear-reserva',
standalone:true,
imports:[CommonModule,FormsModule],
templateUrl:'./crear-reserva.html',
styleUrls:['./crear-reserva.css']
})
export class CrearReservaComponent implements OnInit{

vuelos:any[]=[]
pasajeros:any[]=[]

reserva:any={
vuelo:{idVuelo:null},
pasajero:{idPasajero:null}
}

codigo=""
estado=""

constructor(
private reservaService:ReservaService,
private vueloService:VueloService,
private pasajeroService:PasajeroService
){}

ngOnInit(){

this.vueloService.listar().subscribe(data=>{
this.vuelos=data
})

this.pasajeroService.listar().subscribe(data=>{
this.pasajeros=data
})

}

guardar(){

if(!this.reserva.vuelo.idVuelo || !this.reserva.pasajero.idPasajero){

Swal.fire("Error","Seleccione vuelo y pasajero","error")
return
}

this.reservaService.crear(this.reserva).subscribe({

next:(resp)=>{

this.codigo=resp.codigoReserva || ""
this.estado=resp.estado || ""

Swal.fire(
"Reserva creada",
"Tu código es: "+this.codigo,
"success"
)

},

error:(err)=>{

Swal.fire(
"Error",
err?.error?.message || "No se pudo crear la reserva",
"error"
)

}

})

}

}