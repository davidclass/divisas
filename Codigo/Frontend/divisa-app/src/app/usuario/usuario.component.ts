import { Component, OnInit } from '@angular/core';
import {Usuario} from "./usuario";
import {UsuarioService} from "./usuario.service";



@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  /** VARIABLES **/
  usuarios: Usuario[]=[];
  tituloUsuarios: string =  'Lista de Usuarios';

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.usuarioService.getUsuarios().subscribe(
      (usuarios) => {
        this.usuarios = usuarios
      }
    );
  }

  /** METODOS **/

}
