import { Injectable } from '@angular/core';
import {Usuario} from "./usuario";
import {Observable, throwError } from "rxjs";
import {of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map, catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  /** VARIABLES **/
  private urlEndPoint: string = "http://localhost:8080/api/usuarios";

  constructor(private http: HttpClient) { }

  /** METODOS **/
  getUsuarios(): Observable<Usuario[]>{

    // return this.http.get<Usuario[]>(this.urlEndPoint);
    return this.http.get(this.urlEndPoint).pipe(
      map((response) => response as Usuario[])
    );
  }
}
