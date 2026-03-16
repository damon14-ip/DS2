import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

export interface Pasajero {

  idPasajero?: number
  nombre: string
  apellido: string
  documento: string
  fechaNacimiento: string

}

@Injectable({
  providedIn: 'root'
})
export class PasajeroService {

  private api = "http://localhost:8080/api/pasajeros"

  constructor(private http: HttpClient) {}

  // =========================
  // REGISTRAR PASAJERO
  // =========================
  registrar(pasajero: Pasajero): Observable<Pasajero> {

    return this.http.post<Pasajero>(this.api, pasajero)

  }

  // =========================
  // LISTAR PASAJEROS
  // =========================
  listar(): Observable<Pasajero[]> {

    return this.http.get<Pasajero[]>(this.api)

  }

  // =========================
  // OBTENER POR ID
  // =========================
  obtener(id: number): Observable<Pasajero> {

    return this.http.get<Pasajero>(`${this.api}/${id}`)

  }

  // =========================
  // ACTUALIZAR PASAJERO
  // =========================
  actualizar(id: number, pasajero: Pasajero): Observable<Pasajero> {

    return this.http.put<Pasajero>(`${this.api}/${id}`, pasajero)

  }

  // =========================
  // ELIMINAR PASAJERO
  // =========================
  eliminar(id: number): Observable<any> {

    return this.http.delete(`${this.api}/${id}`)

  }

}