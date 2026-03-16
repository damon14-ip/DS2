import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Checkin{
  idCheckin?:number
  estado?:string
  tarjetaEmbarque?:string
  detalleReserva:{
    idDetalleReserva:number
  }
}

@Injectable({
  providedIn:'root'
})
export class CheckinService{

  private API="http://localhost:8080/api/checkin"

  constructor(private http:HttpClient){}

  realizar(checkin:Checkin):Observable<Checkin>{
    return this.http.post<Checkin>(this.API,checkin)
  }

}