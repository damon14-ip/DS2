import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'

import { CrearVueloComponent } from './crear-vuelo/crear-vuelo'
import { ListarVuelosComponent } from './listar-vuelos/listar-vuelos'

@NgModule({

imports:[
CommonModule,
CrearVueloComponent,
ListarVuelosComponent
],

exports:[
CrearVueloComponent,
ListarVuelosComponent
]

})

export class VuelosModule{}