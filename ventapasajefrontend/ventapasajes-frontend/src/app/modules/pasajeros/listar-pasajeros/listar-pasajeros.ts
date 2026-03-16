import { Component, OnInit } from '@angular/core'
import { CommonModule } from '@angular/common'
import Swal from 'sweetalert2'
import { PasajeroService } from '../../../services/pasajero'

@Component({
  selector: 'app-listar-pasajeros',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './listar-pasajeros.html',
  styleUrls: ['./listar-pasajeros.css']
})
export class ListarPasajerosComponent implements OnInit{

  pasajeros:any[]=[]
  cargando=false

  constructor(private pasajeroService:PasajeroService){}

  ngOnInit(){
    this.cargarPasajeros()
  }

  cargarPasajeros(){

    this.cargando=true

    this.pasajeroService.listar().subscribe({

      next:(data)=>{
        this.pasajeros=data
        this.cargando=false
      },

      error:()=>{
        Swal.fire(
          "Error",
          "No se pudieron cargar los pasajeros",
          "error"
        )
        this.cargando=false
      }

    })

  }

  eliminar(id:number){

    Swal.fire({
      title:"¿Eliminar pasajero?",
      text:"Esta acción no se puede deshacer",
      icon:"warning",
      showCancelButton:true,
      confirmButtonText:"Eliminar",
      cancelButtonText:"Cancelar"
    }).then(result=>{

      if(result.isConfirmed){

        this.pasajeroService.eliminar(id).subscribe(()=>{

          Swal.fire(
            "Eliminado",
            "El pasajero fue eliminado",
            "success"
          )

          this.cargarPasajeros()

        })

      }

    })

  }

}