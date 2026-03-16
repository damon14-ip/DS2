import { NgModule } from '@angular/core';

import { CrearAeropuertoComponent } from './crear-aeropuerto/crear-aeropuerto';
import { ListarAeropuertosComponent } from './listar-aeropuertos/listar-aeropuertos';

@NgModule({
imports:[
CrearAeropuertoComponent,
ListarAeropuertosComponent
],
exports:[
CrearAeropuertoComponent,
ListarAeropuertosComponent
]
})
export class AeropuertosModule{}