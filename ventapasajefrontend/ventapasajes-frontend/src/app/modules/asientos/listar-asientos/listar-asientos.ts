import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AsientoService, Asiento } from '../../../services/asiento';

@Component({
  selector: 'app-listar-asientos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './listar-asientos.html',
  styleUrls: ['./listar-asientos.css']
})
export class ListarAsientosComponent implements OnInit {

  asientos: Asiento[] = [];

  constructor(private asientoService: AsientoService) {}

  ngOnInit() {
    this.cargar();
  }

  cargar() {
    this.asientoService.listar().subscribe(data => {
      this.asientos = data;
    });
  }

  eliminar(id:number){

    if(!confirm("¿Eliminar asiento?")) return;

    this.asientoService.eliminar(id).subscribe(()=>{
      this.cargar();
    });

  }

}