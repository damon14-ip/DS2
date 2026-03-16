import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'

import { PagarReservaComponent } from './pagar-reserva/pagar-reserva'

@NgModule({

imports:[
CommonModule,
PagarReservaComponent
],

exports:[
PagarReservaComponent
]

})
export class PagosModule{}