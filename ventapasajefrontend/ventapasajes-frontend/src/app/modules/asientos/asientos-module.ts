import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CrearAsientoComponent } from './crear-asiento/crear-asiento';
import { ListarAsientosComponent } from './listar-asientos/listar-asientos';

@NgModule({

imports:[
CommonModule,
CrearAsientoComponent,
ListarAsientosComponent
],

exports:[
CrearAsientoComponent,
ListarAsientosComponent
]

})
export class AsientosModule{}