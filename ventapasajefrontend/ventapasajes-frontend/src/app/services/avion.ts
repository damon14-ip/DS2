import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Avion {
  idAvion?: number;
  modelo: string;
  capacidadTotal: number;
  estado?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AvionService {

  private apiUrl = 'http://localhost:8080/api/aviones';

  constructor(private http: HttpClient) {}

  // ===============================
  // LISTAR AVIONES
  // ===============================
  listar(): Observable<Avion[]> {
    return this.http.get<Avion[]>(this.apiUrl);
  }

  // ===============================
  // BUSCAR AVION POR ID
  // ===============================
  buscar(id: number): Observable<Avion> {
    return this.http.get<Avion>(`${this.apiUrl}/${id}`);
  }

  // ===============================
  // CREAR AVION
  // ===============================
  crear(avion: Avion): Observable<Avion> {
    return this.http.post<Avion>(this.apiUrl, avion);
  }

  // ===============================
  // CAMBIAR ESTADO DEL AVION
  // ===============================
  cambiarEstado(id: number, estado: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/estado?estado=${estado}`, {});
  }

  // ===============================
  // ELIMINAR AVION
  // ===============================
  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

}