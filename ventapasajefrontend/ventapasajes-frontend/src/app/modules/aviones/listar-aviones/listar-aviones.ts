import { Component,OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'

import { AvionService,Avion } from '../../../services/avion'

@Component({
selector:'app-listar-aviones',
standalone:true,
imports:[CommonModule],
templateUrl:'./listar-aviones.html',
styleUrls:['./listar-aviones.css']
})
export class ListarAvionesComponent implements OnInit{

aviones:Avion[]=[]

constructor(private avionService:AvionService){}

ngOnInit(){
this.cargar()
}

cargar(){
this.avionService.listar().subscribe({
next:(data)=>{
this.aviones=data
}
})
}

eliminar(id:number){

if(!confirm("¿Eliminar avión?")) return

this.avionService.eliminar(id).subscribe(()=>{
alert("Avión eliminado")
this.cargar()
})

}

cambiarEstado(id:number,estado:string){
this.avionService.cambiarEstado(id,estado).subscribe(()=>{
alert("Estado actualizado")
this.cargar()
})
}

}