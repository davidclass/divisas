import { Component, OnInit } from '@angular/core';
import {Consulta} from "../consulta/consulta";
import {DetalleConsulta} from "./detalle-consulta";
import {ConsultaService} from "../consulta/consulta.service";
import {DetalleConsultaService} from "./detalle-consulta.service";

@Component({
  selector: 'app-detalle-consulta',
  templateUrl: './detalle-consulta.component.html',
  styleUrls: ['./detalle-consulta.component.css']
})
export class DetalleConsultaComponent implements OnInit {

  /** VARIABLES **/
  detalleConsultas: DetalleConsulta[]=[];
  titulodetalleConsultas: string =  'Lista de Detalle Consultas';

  constructor(private detalleConsultaService: DetalleConsultaService) { }

  ngOnInit(): void {
    this.detalleConsultaService.getDetalleConsultas().subscribe(
      (detalleConsultas) => {
        this.detalleConsultas = detalleConsultas
      }
    );
  }

}
