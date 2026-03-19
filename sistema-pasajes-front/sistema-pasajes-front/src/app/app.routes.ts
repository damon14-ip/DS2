import { Routes } from '@angular/router';
import { authGuard } from './guards/auth-guard';

// Componentes Públicos / Generales
import { LoginComponent } from './components/login/login';
import { ReservaFormComponent } from './components/reserva-form/reserva-form';

// Componentes Admin
import { DashboardComponent } from './components/admin/dashboard/dashboard';
import { AvionListComponent } from './components/admin/avion-list/avion-list';
import { AvionFormComponent } from './components/admin/avion-form/avion-form';
import { AeropuertoListComponent } from './components/admin/aeropuerto-list/aeropuerto-list';
import { AeropuertoFormComponent } from './components/admin/aeropuerto-form/aeropuerto-form';
import { VueloListComponent } from './components/admin/vuelo-list/vuelo-list';
import { VueloFormComponent } from './components/admin/vuelo-form/vuelo-form';

// Usuarios
import { UsuarioListComponent } from './components/admin/usuario-list/usuario-list';
import { UsuarioFormComponent } from './components/admin/usuario-form/usuario-form';

// ✅ NUEVO: ASIENTOS
import { AsientoFormComponent } from './components/admin/asiento-form/asiento-form';

export const routes: Routes = [
  { path: 'login', component: LoginComponent },

  { 
    path: 'reservas', 
    component: ReservaFormComponent, 
    canActivate: [authGuard] 
  },

  {
    path: 'admin',
    canActivate: [authGuard],
    data: { role: 'ADMIN' }, 
    children: [
      { path: 'dashboard', component: DashboardComponent },
      
      // Aviones
      { path: 'aviones', component: AvionListComponent },
      { path: 'aviones/nuevo', component: AvionFormComponent },
      { path: 'aviones/editar/:id', component: AvionFormComponent },

      // Aeropuertos
      { path: 'aeropuertos', component: AeropuertoListComponent },
      { path: 'aeropuertos/nuevo', component: AeropuertoFormComponent },
      { path: 'aeropuertos/editar/:id', component: AeropuertoFormComponent },

      // Vuelos
      { path: 'vuelos', component: VueloListComponent },
      { path: 'vuelos/nuevo', component: VueloFormComponent },
      { path: 'vuelos/editar/:id', component: VueloFormComponent },

      // Usuarios
      { path: 'usuarios', component: UsuarioListComponent },
      { path: 'usuarios/nuevo', component: UsuarioFormComponent },

      // ✅ ASIENTOS (NUEVO)
      { path: 'asientos', component: AsientoFormComponent },

      { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
    ]
  },

  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '**', redirectTo: 'login' } 
];