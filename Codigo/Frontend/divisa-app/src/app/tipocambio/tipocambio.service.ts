import { Injectable } from '@angular/core';
import {Observable, throwError} from "rxjs";
import {Usuario} from "../usuario/usuario";
import {map, catchError} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Tipocambio} from "./tipocambio";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class TipocambioService {

  /** VARIABLES **/
  private urlEndPoint: string = "http://localhost:8080/api/tipo-cambio";
  // private urlEndPoint: string = "http://localhost:8080/api/tipo-cambio";

  constructor(private http: HttpClient,
              private router: Router) { }

  /** METODOS **/
  getDivisas(): Observable<Tipocambio[]>{

    // return this.http.get<Usuario[]>(this.urlEndPoint);
    return this.http.get(this.urlEndPoint).pipe(
      map((response) => response as Tipocambio[])
    );
  }

  //Obtener Curso por ID
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
}
