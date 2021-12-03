import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {Usuario} from "../usuario/usuario";
import {map, catchError} from "rxjs/operators";
import {Consulta} from "./consulta";
import {Router} from "@angular/router";
import {Tipocambio} from "../tipocambio/tipocambio";
import {TipocambioService} from "../tipocambio/tipocambio.service";

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {

  /** VARIABLES **/
  private urlEndPoint: string = "http://localhost:8080/api/consultas";

  // Cabeceras
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient,
              private router: Router,
              private tipocambioService: TipocambioService ) { }

  /** METODOS **/
  getConsultas(): Observable<Consulta[]>{

    // return this.http.get<Usuario[]>(this.urlEndPoint);
    return this.http.get(this.urlEndPoint).pipe(
      map((response) => response as Consulta[])
    );
  }

  //Obtener Consulta por ID
  getConsulta(id:number): Observable<Consulta> {
    return this.http.get<Consulta>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {

        /* if (this.isNoAutorizado(e)) {
           return throwError(e);
         }*/
        if (e.status != 401 && e.error.mensaje) {
          this.router.navigate(['/consultas'])
          console.error(e.error.mensaje);
        }


        // Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  createConsulta(consulta: Consulta): Observable<Consulta>{
    return this.http.post<Consulta>(this.urlEndPoint, consulta, {headers: this.httpHeaders});
  }
}
