import { NgModule } from '@angular/core'
import { CommonModule } from '@angular/common'
import { CrearPasajeroComponent } from './crear-pasajero/crear-pasajero'

@NgModule({
  imports: [
    CommonModule,
    CrearPasajeroComponent
  ],
  exports:[
    CrearPasajeroComponent
  ]
})
export class PasajerosModule {}