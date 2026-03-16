import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DetalleReservaService {

  private api = "http://localhost:8080/api/detalle-reserva";

  constructor(private http: HttpClient) {}

  agregarDetalle(data: any): Observable<any> {
    return this.http.post(this.api, data);
  }

  listarDetalles(): Observable<any>{
    return this.http.get(this.api);
  }

}
