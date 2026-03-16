import { Component } from '@angular/core'
import { CommonModule } from '@angular/common'
import { FormsModule } from '@angular/forms'

import { CheckinService } from '../../../services/checkin'

@Component({
selector:'app-realizar-checkin',
standalone:true,
imports:[CommonModule,FormsModule],
templateUrl:'./realizar-checkin.html',
styleUrls:['./realizar-checkin.css']
})
export class RealizarCheckinComponent{

detalleReserva:number=0
mensaje=""
tarjeta=""

constructor(private checkinService:CheckinService){}

realizar(){

if(!this.detalleReserva){
this.mensaje="Ingrese el ID de detalle de reserva"
return
}

const data={
detalleReserva:{
idDetalleReserva:this.detalleReserva
}
}

this.checkinService.realizar(data).subscribe({

next:(resp)=>{

this.mensaje="✅ Check-in realizado correctamente"
this.tarjeta=resp.tarjetaEmbarque || ""

},

error:(err)=>{

this.mensaje=err?.error?.message || "Error al realizar check-in"

}

})

}

}