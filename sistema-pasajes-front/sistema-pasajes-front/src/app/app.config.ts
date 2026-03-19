import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router'; // FALTA ESTO
import { provideHttpClient,withInterceptors } from '@angular/common/http'; // FALTA ESTO
import { routes } from './app.routes'; // IMPORTA TUS RUTAS
import { authInterceptor } from './interceptors/auth';
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),      // <--- ESTO ACTIVA LAS RUTAS
   provideHttpClient(
      withInterceptors([authInterceptor]) 
    )
  ]
};