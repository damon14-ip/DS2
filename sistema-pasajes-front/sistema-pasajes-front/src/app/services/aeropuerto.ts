import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AeropuertoService {
  private API_URL = 'http://localhost:8080/api/aeropuertos';

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<any[]> {
    return this.http.get<any[]>(this.API_URL);
  }

  registrar(dto: any): Observable<any> {
    return this.http.post(this.API_URL, dto);
  }

  actualizar(id: number, dto: any): Observable<any> {
    return this.http.put(`${this.API_URL}/${id}`, dto);
  }

  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.API_URL}/${id}`);
  }
}