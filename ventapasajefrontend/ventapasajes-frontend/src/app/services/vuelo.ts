import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

export interface Vuelo{

idVuelo?:number

aeropuertoOrigen:{
idAeropuerto:number
}

aeropuertoDestino:{
idAeropuerto:number
}

horaSalida:string
horaLlegada:string
estado?:string

}

@Injectable({
providedIn:'root'
})

export class VueloService{

private API="http://localhost:8080/api/vuelos"

constructor(private http:HttpClient){}

listar():Observable<Vuelo[]>{
return this.http.get<Vuelo[]>(this.API)
}

programar(vuelo:Vuelo):Observable<Vuelo>{
return this.http.post<Vuelo>(this.API,vuelo)
}

cambiarEstado(id:number,estado:string){
return this.http.put(`${this.API}/${id}/estado?estado=${estado}`,{})
}

}