import { Component, OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'
import Swal from 'sweetalert2'

import { AeropuertoService, Aeropuerto } from '../../../services/aeropuerto'

@Component({
  selector:'app-listar-aeropuertos',
  standalone:true,
  imports:[CommonModule],   // 👈 IMPORTANTE
  templateUrl:'./listar-aeropuertos.html',
  styleUrls:['./listar-aeropuertos.css']
})
export class ListarAeropuertosComponent implements OnInit{

  aeropuertos:Aeropuerto[]=[]
  cargando=false

  constructor(private aeropuertoService:AeropuertoService){}

  ngOnInit(){
    this.cargar()
  }

  cargar(){

    this.cargando=true

    this.aeropuertoService.listar()
    .subscribe({

      next:(data)=>{
        this.aeropuertos=data
        this.cargando=false
      },

      error:()=>{
        Swal.fire("Error","No se pudo cargar","error")
        this.cargando=false
      }

    })

  }

  eliminar(id:number){

    Swal.fire({
      title:"¿Eliminar aeropuerto?",
      icon:"warning",
      showCancelButton:true
    }).then(result=>{

      if(result.isConfirmed){

        this.aeropuertoService.eliminar(id)
        .subscribe(()=>{

          Swal.fire("Eliminado","","success")
          this.cargar()

        })

      }

    })

  }

}