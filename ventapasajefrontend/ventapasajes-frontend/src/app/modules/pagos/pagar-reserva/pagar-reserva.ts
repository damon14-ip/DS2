import { Component } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'

import { PagoService } from '../../../services/pago'

@Component({
selector:'app-pagar-reserva',
standalone:true,
imports:[CommonModule,FormsModule],
templateUrl:'./pagar-reserva.html',
styleUrls:['./pagar-reserva.css']
})
export class PagarReservaComponent{

idPago:number=0
mensaje=""
estado=""

constructor(private pagoService:PagoService){}

aprobar(){

if(!this.idPago){
this.mensaje="Ingrese el ID del pago"
return
}

this.pagoService.aprobarPago(this.idPago).subscribe({

next:(resp)=>{
this.mensaje="✅ Pago aprobado correctamente"
this.estado=resp.estado || ""
},

error:(err)=>{
this.mensaje=err?.error?.message || "Error al aprobar el pago"
}

})

}

}