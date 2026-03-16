import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthService } from './auth';
import { AeropuertoService } from './aeropuerto';
import { AvionService } from './avion';
import { AsientoService } from './asiento';
import { VueloService } from './vuelo';
import { ReservaService } from './reserva';
import { DetalleReservaService } from './detalle-reserva';
import { PasajeroService } from './pasajero';
import { PagoService } from './pago';
import { CheckinService } from './checkin';
import { UsuarioService } from './usuario';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    AuthService,
    AeropuertoService,
    AvionService,
    AsientoService,
    VueloService,
    ReservaService,
    DetalleReservaService,
    PasajeroService,
    PagoService,
    CheckinService,
    UsuarioService
  ]
})
export class ServicesModule { }
