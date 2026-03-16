import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Asiento {
  idAsiento?: number;
  numero: string;
  tipo: string;
  precioExtra: number;
  avion: any;
}

@Injectable({
  providedIn: 'root'
})
export class AsientoService {

  private apiUrl = 'http://localhost:8080/api/asientos';

  constructor(private http: HttpClient) {}

  // LISTAR TODOS
  listar(): Observable<Asiento[]> {
    return this.http.get<Asiento[]>(this.apiUrl);
  }

  // LISTAR POR AVION
  listarPorAvion(idAvion:number): Observable<Asiento[]>{
    return this.http.get<Asiento[]>(`${this.apiUrl}/avion/${idAvion}`);
  }

  // CREAR
  crear(asiento:Asiento): Observable<Asiento>{
    return this.http.post<Asiento>(this.apiUrl, asiento);
  }

  // ELIMINAR
  eliminar(id:number): Observable<any>{
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

}