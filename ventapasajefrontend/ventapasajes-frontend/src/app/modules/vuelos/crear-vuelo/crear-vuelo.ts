import { Component } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
import Swal from 'sweetalert2'

import { VueloService } from '../../../services/vuelo'

@Component({

selector:'app-crear-vuelo',
standalone:true,
imports:[CommonModule,FormsModule],
templateUrl:'./crear-vuelo.html',
styleUrls:['./crear-vuelo.css']

})

export class CrearVueloComponent{

vuelo:any={

aeropuertoOrigen:{idAeropuerto:null},
aeropuertoDestino:{idAeropuerto:null},
horaSalida:'',
horaLlegada:''

}

constructor(private vueloService:VueloService){}

guardar(){

if(!this.vuelo.aeropuertoOrigen.idAeropuerto ||
!this.vuelo.aeropuertoDestino.idAeropuerto){

Swal.fire("Error","Ingrese aeropuertos","error")
return

}

this.vueloService.programar(this.vuelo).subscribe({

next:()=>{

Swal.fire(
"Vuelo creado",
"El vuelo fue programado correctamente",
"success"
)

this.vuelo={
aeropuertoOrigen:{idAeropuerto:null},
aeropuertoDestino:{idAeropuerto:null},
horaSalida:'',
horaLlegada:''
}

},

error:(err)=>{

Swal.fire(
"Error",
err?.error?.message || "No se pudo crear el vuelo",
"error"
)

}

})

}

}