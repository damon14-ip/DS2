import { Injectable, signal, computed } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap, catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private API_URL = 'http://localhost:8080/api/auth';
  
  // Signal que contiene el estado del usuario (null si no está logueado)
  currentUser = signal<any>(null);

  // Computed signals para acceso rápido (reactivos)
  isAuthenticated = computed(() => !!this.currentUser());
  userRole = computed(() => this.currentUser()?.rol);

  constructor(private http: HttpClient, private router: Router) {
    this.checkSession(); // Intentar recuperar la sesión al cargar la App
  }

  /**
   * Intenta recuperar los datos del usuario guardados en el navegador
   */
  private checkSession() {
    const token = localStorage.getItem('token');
    const userJson = localStorage.getItem('user');

    if (token && userJson) {
      try {
        this.currentUser.set(JSON.parse(userJson));
      } catch (e) {
        this.logout(); // Si el JSON está corrupto, limpiamos todo
      }
    }
  }

  /**
   * Envía las credenciales al Backend
   */
  login(credentials: { email: string; password: string }) {
    return this.http.post(`${this.API_URL}/login`, credentials).pipe(
      tap((res: any) => {
        // res debe traer: { token: string, email: string, rol: string }
        localStorage.setItem('token', res.token);
        
        const userData = {
          email: res.email,
          rol: res.rol
        };

        // Guardamos el objeto usuario como string en localStorage
        localStorage.setItem('user', JSON.stringify(userData));
        
        // Actualizamos el Signal (esto hará que el Navbar se actualice solo)
        this.currentUser.set(userData);
      }),
      catchError(err => {
        console.error('Error en login:', err);
        return throwError(() => err);
      })
    );
  }

  /**
   * Registro de nuevos usuarios
   */
  register(userData: any) {
    return this.http.post(`${this.API_URL}/registro`, userData);
  }

  /**
   * Limpia la sesión y redirige al inicio
   */
  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.currentUser.set(null);
    this.router.navigate(['/login']);
  }

  /**
   * Método útil para obtener el token desde un interceptor
   */
  getToken(): string | null {
    return localStorage.getItem('token');
  }
}