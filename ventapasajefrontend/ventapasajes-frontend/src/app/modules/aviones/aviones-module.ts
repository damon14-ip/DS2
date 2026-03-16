import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'

import { CrearAvionComponent } from './crear-avion/crear-avion'
import { ListarAvionesComponent } from './listar-aviones/listar-aviones'

@NgModule({

imports:[
CommonModule,
CrearAvionComponent,
ListarAvionesComponent
],

exports:[
CrearAvionComponent,
ListarAvionesComponent
]

})
export class AvionesModule{}