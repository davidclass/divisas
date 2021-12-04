import { Component, OnInit } from '@angular/core';
import {Consulta} from "../../consulta/consulta";
import {ConsultaService} from "../../consulta/consulta.service";
import {ActivatedRoute} from "@angular/router";
import {DetalleConsulta} from "../detalle-consulta";
import {DetalleConsultaService} from "../detalle-consulta.service";

@Component({
  selector: 'app-detalle',
  templateUrl: './detalle.component.html',
  styleUrls: ['./detalle.component.css']
})
export class DetalleComponent implements OnInit {

  /** VARIABLES **/
  tituloDetalle: string = 'Detalle Consulra'
  consulta: Consulta = new Consulta();
  detalleConsulta: DetalleConsulta = new DetalleConsulta();

  constructor(private consultaService: ConsultaService,
              private detalleConsultaService: DetalleConsultaService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params =>{
      //Con el signo + convertimos en un tipo number
      let id = params.get('id');

      if (id) {
        this.detalleConsultaService.getDetalleConsulta(id).subscribe(detalleConsulta => {
          // this.docente.disponibilidades as Disponibilidad[];

          this.detalleConsulta = detalleConsulta;
          // this.docente.disponibilidades = docente.disponibilidades;
          // console.log(typeof this.docente.disponibilidades)
          // console.log('detalle.componente.ngOnInit:' + this.docente)
        });
      }
    });
  }

}
