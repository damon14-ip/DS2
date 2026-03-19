import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {
  private API_URL = 'http://localhost:8080/api/reservas';

  constructor(private http: HttpClient) {}

  /**
   * Método usado por VentaPasajeComponent
   */
  crearReservaYDescargarPdf(payload: any): Observable<Blob> {
    return this.http.post(`${this.API_URL}/vender`, payload, {
      responseType: 'blob'
    });
  }

  /**
   * Método usado por ReservaFormComponent (Alias del anterior para compatibilidad)
   */
  enviarReserva(payload: any): Observable<Blob> {
    return this.crearReservaYDescargarPdf(payload);
  }
}