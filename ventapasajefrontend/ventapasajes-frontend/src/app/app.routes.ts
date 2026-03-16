import { Routes } from '@angular/router';
import { AuthGuard } from './core/auth-guard';

// Auth
import { LoginComponent } from './modules/auth/login/login';
import { RegistroComponent } from './modules/auth/registro/registro';

// Aeropuertos
import { ListarAeropuertosComponent } from './modules/aeropuertos/listar-aeropuertos/listar-aeropuertos';
import { CrearAeropuertoComponent } from './modules/aeropuertos/crear-aeropuerto/crear-aeropuerto';

// Vuelos
import { ListarVuelosComponent } from './modules/vuelos/listar-vuelos/listar-vuelos';
import { CrearVueloComponent } from './modules/vuelos/crear-vuelo/crear-vuelo';

// Aviones
import { ListarAvionesComponent } from './modules/aviones/listar-aviones/listar-aviones';
import { CrearAvionComponent } from './modules/aviones/crear-avion/crear-avion';

// Asientos
import { ListarAsientosComponent } from './modules/asientos/listar-asientos/listar-asientos';
import { CrearAsientoComponent } from './modules/asientos/crear-asiento/crear-asiento';

// Reservas
import { ListarReservasComponent } from './modules/reservas/listar-reservas/listar-reservas';
import { CrearReservaComponent } from './modules/reservas/crear-reserva/crear-reserva';

// Pasajeros
import { CrearPasajeroComponent } from './modules/pasajeros/crear-pasajero/crear-pasajero';
import { ListarPasajerosComponent } from './modules/pasajeros/listar-pasajeros/listar-pasajeros';
// Pagos
import { PagarReservaComponent } from './modules/pagos/pagar-reserva/pagar-reserva';

// Checkin
import { RealizarCheckinComponent } from './modules/checkin/realizar-checkin/realizar-checkin';
//detalle resserva
import { CrearDetalleReservaComponent } from './modules/detalle-reserva/crear-detalle-reserva/crear-detalle-reserva';
import { ListarDetalleReservaComponent } from './modules/detalle-reserva/listar-detalle-reserva/listar-detalle-reserva';

export const routes: Routes = [

  // =====================
  // REDIRECCIÓN INICIAL
  // =====================
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // =====================
  // AUTENTICACIÓN
  // =====================
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },

  // =====================
  // AEROPUERTOS
  // =====================
  {
    path: 'aeropuertos',
    component: ListarAeropuertosComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'aeropuertos/crear',
    component: CrearAeropuertoComponent,
    canActivate: [AuthGuard]
  },

  // =====================
  // VUELOS
  // =====================
  {
    path: 'vuelos',
    component: ListarVuelosComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'vuelos/crear',
    component: CrearVueloComponent,
    canActivate: [AuthGuard]
  },

  // =====================
  // AVIONES
  // =====================
  {
    path: 'aviones',
    component: ListarAvionesComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'aviones/crear',
    component: CrearAvionComponent,
    canActivate: [AuthGuard]
  },

  // =====================
  // ASIENTOS
  // =====================
  {
    path: 'asientos',
    component: ListarAsientosComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'asientos/crear',
    component: CrearAsientoComponent,
    canActivate: [AuthGuard]
  },

  // =====================
  // RESERVAS
  // =====================
  {
    path: 'reservas',
    component: ListarReservasComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'reservas/crear/:idVuelo',
    component: CrearReservaComponent,
    canActivate: [AuthGuard]
  },

  // =====================
  // PASAJEROS
  // =====================
  {
    path: 'pasajeros/crear/:idReserva',
    component: CrearPasajeroComponent,
    canActivate: [AuthGuard]
  },

  {
  path: 'pasajeros',
  component: ListarPasajerosComponent,
  canActivate: [AuthGuard]
},
  // =====================
  // PAGOS
  // =====================
  {
    path: 'pagos/:idReserva',
    component: PagarReservaComponent,
    canActivate: [AuthGuard]
  },

  // =====================
  // CHECKIN
  // =====================
  {
    path: 'checkin/:codigoReserva',
    component: RealizarCheckinComponent,
    canActivate: [AuthGuard]
  },
  // =====================
// DETALLE RESERVA
// =====================

{
  path:'detalle-reserva',
  component: ListarDetalleReservaComponent,
  canActivate:[AuthGuard]
},

{
  path:'detalle-reserva/crear',
  component: CrearDetalleReservaComponent,
  canActivate:[AuthGuard]
},


  // =====================
  // 404
  // =====================
  {
    path: '**',
    redirectTo: 'login'
  }

];