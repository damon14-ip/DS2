import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

export interface Reserva{
  idReserva?: number
  codigoReserva?: string
  estado?: string
  fechaExpiracion?: string

  vuelo?:{
    idVuelo:number
  }

  pasajero?:{
    idPasajero:number
  }
}

@Injectable({
  providedIn:'root'
})
export class ReservaService{

  private API="http://localhost:8080/api/reservas"

  constructor(private http:HttpClient){}

  // ===============================
  // LISTAR RESERVAS
  // ===============================
  listar():Observable<Reserva[]>{
    return this.http.get<Reserva[]>(this.API)
  }

  // ===============================
  // CREAR RESERVA
  // ===============================
  crear(reserva:Reserva):Observable<Reserva>{
    return this.http.post<Reserva>(this.API,reserva)
  }

  // ===============================
  // CONFIRMAR RESERVA
  // ===============================
  confirmar(id:number):Observable<Reserva>{
    return this.http.put<Reserva>(`${this.API}/${id}/confirmar`,{})
  }

}