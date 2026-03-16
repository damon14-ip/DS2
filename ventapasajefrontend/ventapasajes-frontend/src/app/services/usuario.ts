import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private api = "http://localhost:8080/api/usuarios";

  constructor(private http: HttpClient) {}

  listar(): Observable<any> {
    return this.http.get(this.api);
  }

  buscar(id: number): Observable<any> {
    return this.http.get(`${this.api}/${id}`);
  }

  registrar(data: any): Observable<any> {
    // El endpoint de registro es específico
    return this.http.post(`${this.api}/registro`, data);
  }

  desactivar(id: number): Observable<any> {
    return this.http.put(`${this.api}/${id}/desactivar`, {});
  }
}
