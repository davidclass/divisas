import { Component, OnInit } from '@angular/core';
import {Usuario} from "../usuario/usuario";
import {UsuarioService} from "../usuario/usuario.service";
import {ConsultaService} from "./consulta.service";
import {Consulta} from "./consulta";

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  /** VARIABLES **/
  consultas: Consulta[]=[];
  tituloConsultas: string =  'Lista de Consultas';

  constructor(private consultaService: ConsultaService) { }

  ngOnInit(): void {
    this.consultaService.getConsultas().subscribe(
      (consultas) => {
        this.consultas = consultas
      }
    );
  }

}
