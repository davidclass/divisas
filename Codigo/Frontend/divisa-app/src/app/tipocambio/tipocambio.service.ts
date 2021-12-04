import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {Usuario} from "../usuario/usuario";
import {map, catchError} from "rxjs/operators";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Tipocambio} from "./tipocambio";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class TipocambioService {

  /** VARIABLES **/
  private urlEndPoint: string = "http://localhost:8080/api/tipo-cambio";

  // Cabeceras
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient,
              private router: Router) { }

  /** METODOS **/
  getDivisas(): Observable<Tipocambio[]>{

    // return this.http.get<Usuario[]>(this.urlEndPoint);
    return this.http.get(this.urlEndPoint).pipe(
      map((response) => response as Tipocambio[])
    );
  }

  createDivisa(tipoCambio: Tipocambio): Observable<Tipocambio>{
    return this.http.post<Tipocambio>(this.urlEndPoint, tipoCambio, {headers: this.httpHeaders});
  }

  //Obtener DIVISA por ID
  getDivisa(id: string): Observable<Tipocambio> {
    return this.http.get<Tipocambio>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {

        /* if (this.isNoAutorizado(e)) {
           return throwError(e);
         }*/
        if (e.status != 401 && e.error.mensaje) {
          this.router.navigate(['/tipo-cambio'])
          console.error(e.error.mensaje);
        }


        // Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  //Editar un DIVISA
  updateDivisa(tipoCambio: Tipocambio): Observable<Tipocambio>{
    return this.http.put<Tipocambio>(`${this.urlEndPoint}/${tipoCambio.id}`, tipoCambio, {headers: this.httpHeaders} );
  }

  //Eliminar un DIVISA
  deleteDivisa(id: number | undefined): Observable<Tipocambio> {
    return this.http.delete<Tipocambio>(`${this.urlEndPoint}/${id}`);
  }

}
