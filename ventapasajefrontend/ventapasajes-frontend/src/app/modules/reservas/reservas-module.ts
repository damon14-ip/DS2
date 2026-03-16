import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'

import { CrearReservaComponent } from './crear-reserva/crear-reserva'
import { ListarReservasComponent } from './listar-reservas/listar-reservas'

@NgModule({

imports:[
CommonModule,
CrearReservaComponent,
ListarReservasComponent
],

exports:[
CrearReservaComponent,
ListarReservasComponent
]

})
export class ReservasModule{}