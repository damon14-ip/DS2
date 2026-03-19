import { Injectable } from '@angular/core'; // CORRECCIÓN: Viene de @angular/core
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AvionService {
  private API_URL = 'http://localhost:8080/api/aviones';

  constructor(private http: HttpClient) {}

  listarTodos(): Observable<any[]> {
    return this.http.get<any[]>(this.API_URL);
  }

  buscarPorId(id: number): Observable<any> {
    return this.http.get<any>(`${this.API_URL}/${id}`);
  }

  registrar(avionDto: any): Observable<any> {
    return this.http.post(this.API_URL, avionDto);
  }

  actualizar(id: number, avionDto: any): Observable<any> {
    return this.http.put(`${this.API_URL}/${id}`, avionDto);
  }

  listarPorAeropuerto(idAeropuerto: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.API_URL}/aeropuerto/${idAeropuerto}`);
  }

  eliminar(id: number): Observable<any> {
    return this.http.delete(`${this.API_URL}/${id}`);
  }
}