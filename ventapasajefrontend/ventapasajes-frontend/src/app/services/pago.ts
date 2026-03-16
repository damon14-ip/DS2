import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

export interface Pago{
  idPago?:number
  estado?:string
  reserva?:{
    idReserva:number
    estado?:string
  }
}

@Injectable({
  providedIn:'root'
})
export class PagoService{

  private API="http://localhost:8080/api/pagos"

  constructor(private http:HttpClient){}

  aprobarPago(id:number):Observable<Pago>{
    return this.http.put<Pago>(`${this.API}/${id}/aprobar`,{})
  }

}