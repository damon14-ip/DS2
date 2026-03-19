import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PasajeroService {
  private API_URL = 'http://localhost:8080/api/pasajeros';

  constructor(private http: HttpClient) {}

  // Este método es el que usará el componente de venta
  buscarPorDocumento(dni: string): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/documento/${dni}`);
  }

  listarTodos(): Observable<any[]> {
    return this.http.get<any[]>(this.API_URL);
  }
}