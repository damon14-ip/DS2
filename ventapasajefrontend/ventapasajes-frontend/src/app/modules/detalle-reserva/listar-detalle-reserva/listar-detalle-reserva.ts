import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DetalleReservaService } from '../../../services/detalle-reserva';

@Component({
  selector: 'app-listar-detalle-reserva',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './listar-detalle-reserva.html',
  styleUrls: ['./listar-detalle-reserva.css']
})
export class ListarDetalleReservaComponent implements OnInit {

  detalles:any[]=[];

  constructor(private service:DetalleReservaService){}

  ngOnInit(): void {
    this.cargarDetalles();
  }

  cargarDetalles(){
    this.service.listarDetalles().subscribe({
      next:(data)=>{
        this.detalles=data;
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

}
