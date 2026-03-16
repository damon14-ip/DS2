import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private api = 'http://localhost:8080/api/auth';
  private tokenKey = 'token';
  private usuarioKey = 'usuario';
  
  private isLoggedIn$ = new BehaviorSubject<boolean>(this.obtenerToken() !== null);

  constructor(private http: HttpClient) {
    console.log('🔐 AuthService inicializado, token actual:', this.obtenerToken() ? 'SÍ' : 'NO');
  }

  login(data: any): Observable<any> {
    console.log('📤 Enviando login:', data);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<any>(`${this.api}/login`, data, { headers }).pipe(
      tap((response: any) => {
        console.log('✅ Respuesta del servidor:', response);
        
        if (response?.token) {
          console.log('💾 Guardando token en localStorage...');
          localStorage.setItem(this.tokenKey, response.token);
          console.log('✔️ Token guardado:', this.obtenerToken() ? 'SÍ' : 'NO');

          // Ajustado para coincidir con la respuesta del backend
          if (response?.email && response?.rol) {
            const usuario = { email: response.email, rol: response.rol };
            localStorage.setItem(this.usuarioKey, JSON.stringify(usuario));
            console.log('✔️ Usuario guardado:', usuario);
          }
          
          this.isLoggedIn$.next(true);
        } else {
          console.warn('⚠️ No hay token en la respuesta:', response);
        }
      }),
      catchError((error) => {
        console.error('❌ Error en login:', error);
        return of(error);
      })
    );
  }

  registrar(data: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<any>('http://localhost:8080/api/usuarios/registro', data, { headers }).pipe(
      tap((response: any) => {
        if (response?.token) {
          localStorage.setItem(this.tokenKey, response.token);
          this.isLoggedIn$.next(true);
        }
      })
    );
  }

  obtenerToken(): string | null {
    const token = localStorage.getItem(this.tokenKey);
    return token;
  }

  guardarToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
    console.log('✔️ Token guardado manualmente');
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.usuarioKey);
    this.isLoggedIn$.next(false);
    console.log('🚪 Sesión cerrada');
  }

  estaAutenticado(): boolean {
    return this.obtenerToken() !== null;
  }

  getIsLoggedIn$(): Observable<boolean> {
    return this.isLoggedIn$.asObservable();
  }
}