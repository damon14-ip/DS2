import { Component, OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'
import Swal from 'sweetalert2'

import { ReservaService } from '../../../services/reserva'

@Component({
selector:'app-listar-reservas',
standalone:true,
imports:[CommonModule],
templateUrl:'./listar-reservas.html',
styleUrls:['./listar-reservas.css']
})
export class ListarReservasComponent implements OnInit{

reservas:any[]=[]
cargando=false

constructor(private reservaService:ReservaService){}

ngOnInit(){
this.cargarReservas()
}

cargarReservas(){

this.cargando=true

this.reservaService.listar().subscribe({

next:(data)=>{

this.reservas=data
this.cargando=false

},

error:()=>{

Swal.fire(
"Error",
"No se pudieron cargar las reservas",
"error"
)

this.cargando=false

}

})

}

confirmar(id:number){

Swal.fire({
title:"Confirmar reserva",
text:"¿Deseas confirmar esta reserva?",
icon:"question",
showCancelButton:true,
confirmButtonText:"Si confirmar"
}).then(result=>{

if(result.isConfirmed){

this.reservaService.confirmar(id).subscribe({

next:()=>{

Swal.fire(
"Reserva confirmada",
"La reserva fue confirmada correctamente",
"success"
)

this.cargarReservas()

},

error:()=>{

Swal.fire(
"Error",
"No se pudo confirmar la reserva",
"error"
)

}

})

}

})

}

}