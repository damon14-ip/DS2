import { Component } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'
import Swal from 'sweetalert2'

import { AvionService } from '../../../services/avion'

@Component({
selector:'app-crear-avion',
standalone:true,
imports:[CommonModule,FormsModule],
templateUrl:'./crear-avion.html',
styleUrls:['./crear-avion.css']
})
export class CrearAvionComponent{

avion={
modelo:'',
capacidadTotal:0
}

constructor(private avionService:AvionService){}

guardar(){

if(!this.avion.modelo || this.avion.capacidadTotal<=0){

Swal.fire({
icon:'error',
title:'Error',
text:'Completa los campos correctamente'
})

return
}

this.avionService.crear(this.avion).subscribe({

next:()=>{

Swal.fire({
icon:'success',
title:'Avión registrado',
text:'✈️ El avión fue registrado correctamente'
})

this.reset()

},

error:(err)=>{

Swal.fire({
icon:'error',
title:'Error',
text: err?.error?.message || "Error al registrar avión"
})

}

})

}

reset(){

this.avion={
modelo:'',
capacidadTotal:0
}

}

}