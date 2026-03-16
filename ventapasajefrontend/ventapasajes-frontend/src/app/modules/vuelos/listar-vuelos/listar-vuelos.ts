import { Component,OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'
import Swal from 'sweetalert2'

import { VueloService } from '../../../services/vuelo'

@Component({

selector:'app-listar-vuelos',
standalone:true,
imports:[CommonModule],
templateUrl:'./listar-vuelos.html',
styleUrls:['./listar-vuelos.css']

})

export class ListarVuelosComponent implements OnInit{

vuelos:any[]=[]
cargando=false

constructor(private vueloService:VueloService){}

ngOnInit(){
this.cargar()
}

cargar(){

this.cargando=true

this.vueloService.listar().subscribe({

next:(data)=>{
this.vuelos=data
this.cargando=false
},

error:()=>{
Swal.fire("Error","No se pudieron cargar los vuelos","error")
this.cargando=false
}

})

}

cambiarEstado(id:number,estado:string){

this.vueloService.cambiarEstado(id,estado).subscribe({

next:()=>{

Swal.fire(
"Estado cambiado",
"Estado actualizado correctamente",
"success"
)

this.cargar()

},

error:()=>{
Swal.fire("Error","No se pudo cambiar el estado","error")
}

})

}

}