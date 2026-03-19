import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const user = authService.currentUser();

  // 1. Verificación de Autenticación
  if (!user) {
    return router.createUrlTree(['/login']); // Mejor práctica que navigate()
  }

  // 2. Verificación de Autorización por Rol
  // Buscamos si la ruta tiene un rol requerido definido en data: { role: 'ADMIN' }
  const expectedRole = route.data['role'];

  if (expectedRole && user.rol !== expectedRole) {
    alert(`Acceso denegado: Se requiere rol de ${expectedRole}`);
    return router.createUrlTree(['/reservas']);
  }

  return true;
};