import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, throwError} from "rxjs";
import {Consulta} from "../consulta/consulta";
import {catchError, map} from "rxjs/operators";
import {DetalleConsulta} from "./detalle-consulta";

@Injectable({
  providedIn: 'root'
})
export class DetalleConsultaService {

  /** VARIABLES **/
  private urlEndPoint: string = "http://localhost:8080/api/detalle-consulta";

  constructor(private http: HttpClient,
              private router: Router) {

  }

  /** METODOS **/
  getDetalleConsultas(): Observable<DetalleConsulta[]>{

    return this.http.get(this.urlEndPoint).pipe(
      map((response) => response as DetalleConsulta[])
    );
  }

  //Obtener Consulta por ID
  getDetalleConsulta(id: string): Observable<DetalleConsulta> {
    return this.http.get<DetalleConsulta>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {

        /* if (this.isNoAutorizado(e)) {
           return throwError(e);
         }*/
        if (e.status != 401 && e.error.mensaje) {
          this.router.navigate(['/detalle-consulta'])
          console.error(e.error.mensaje);
        }


        // Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }
}
