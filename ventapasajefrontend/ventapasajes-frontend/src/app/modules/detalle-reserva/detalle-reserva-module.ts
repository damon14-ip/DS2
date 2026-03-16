import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CrearDetalleReservaComponent } from './crear-detalle-reserva/crear-detalle-reserva';
import { ListarDetalleReservaComponent } from './listar-detalle-reserva/listar-detalle-reserva';

@NgModule({
  declarations:[],
  imports:[
    CommonModule,
    CrearDetalleReservaComponent,
    ListarDetalleReservaComponent
  ],
  exports:[
    CrearDetalleReservaComponent,
    ListarDetalleReservaComponent
  ]
})
export class DetalleReservaModule{}
