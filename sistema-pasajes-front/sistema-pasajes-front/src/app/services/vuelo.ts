import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VueloService {
  private API_URL = 'http://localhost:8080/api/vuelos';

  constructor(private http: HttpClient) {}

  // Para el Admin: Listar todos
  listarTodos(): Observable<any[]> {
    return this.http.get<any[]>(this.API_URL);
  }

  // Para el buscador: Filtrar por origen/destino
  buscarVuelos(origenId: number, destinoId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.API_URL}/buscar?origen=${origenId}&destino=${destinoId}`);
  }

  crearVuelo(vuelo: any): Observable<any> {
    return this.http.post(this.API_URL, vuelo);
  }

  eliminarVuelo(id: number): Observable<any> {
    return this.http.delete(`${this.API_URL}/${id}`);
  }
}