import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AsientoService {

  // 🔥 CORREGIDO (agregamos /api)
  private api = 'http://localhost:8080/api/asientos';

  constructor(private http: HttpClient) {}

  // ✅ LISTAR POR AVIÓN
  listarPorAvion(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/avion/${id}`);
  }

  // ✅ LISTAR DISPONIBLES
  listarDisponibles(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/disponibles/${id}`);
  }

  // ✅ BUSCAR POR CÓDIGO
  buscar(avionId: number, codigo: string): Observable<any> {
    return this.http.get<any>(
      `${this.api}/buscar?avionId=${avionId}&codigo=${codigo}`
    );
  }

  // ✅ CREAR
  crear(asiento: any): Observable<any> {
    return this.http.post<any>(this.api, asiento);
  }

  // ❌ ACTUALIZAR (no existe en tu backend → opcional eliminar)
  actualizar(id: number, asiento: any): Observable<any> {
    return this.http.put<any>(`${this.api}/${id}`, asiento);
  }

  // ✅ OCUPAR ASIENTO
  ocupar(id: number): Observable<any> {
    return this.http.put(`${this.api}/ocupar/${id}`, {});
  }

  // ✅ LIBERAR ASIENTO
  liberar(id: number): Observable<any> {
    return this.http.put(`${this.api}/liberar/${id}`, {});
  }
}
