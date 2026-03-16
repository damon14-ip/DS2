import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Aeropuerto{
  idAeropuerto?: number;
  nombre: string;
  ciudad: string;
  pais: string;
}

@Injectable({
  providedIn: 'root'
})
export class AeropuertoService {

  private api = "http://localhost:8080/api/aeropuertos";

  constructor(private http: HttpClient){}

  listar(): Observable<Aeropuerto[]>{
    return this.http.get<Aeropuerto[]>(this.api);
  }

  buscar(id:number): Observable<Aeropuerto>{
    return this.http.get<Aeropuerto>(`${this.api}/${id}`);
  }

  crear(data:Aeropuerto): Observable<Aeropuerto>{
    return this.http.post<Aeropuerto>(this.api,data);
  }

  actualizar(id:number,data:Aeropuerto): Observable<Aeropuerto>{
    return this.http.put<Aeropuerto>(`${this.api}/${id}`,data);
  }

  eliminar(id:number){
    return this.http.delete(`${this.api}/${id}`);
  }

}