import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario, UsuarioDTO } from '../models/user'; // Importamos el modelo

@Injectable({ providedIn: 'root' })
export class UserService {
  private API_URL = 'http://localhost:8080/api/usuarios';

  constructor(private http: HttpClient) {}

  // Ahora sabemos que devuelve una lista de DTOs (con nombre completo y dni)
  listar(): Observable<UsuarioDTO[]> {
    return this.http.get<UsuarioDTO[]>(this.API_URL);
  }

  // Ahora sabemos que recibe un objeto tipo Usuario
  crear(usuario: Usuario): Observable<any> {
    return this.http.post(this.API_URL, usuario, { responseType: 'text' });
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}`);
  }
}