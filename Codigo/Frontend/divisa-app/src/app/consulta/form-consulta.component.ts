import { Component, OnInit } from '@angular/core';
import {Tipocambio} from "../tipocambio/tipocambio";
import {Consulta} from "./consulta";
import {ActivatedRoute, Router} from "@angular/router";
import {TipocambioService} from "../tipocambio/tipocambio.service";
import {ConsultaService} from "./consulta.service";

@Component({
  selector: 'app-form',
  templateUrl: './form-consulta.component.html',
  styleUrls: ['./form-consulta.component.css']
})
export class FormConsultaComponent implements OnInit {

  /** VARIABLES **/
  public consulta: Consulta = new Consulta();
  public tituloFormConsulta: string = 'FORMULARIO CONSULTA';
  public tiposCambioOrigen: Tipocambio[] = [];
  public tiposCambioDestino: Tipocambio[] = [];

  constructor(private consultaService: ConsultaService,
              private tipoCambioService: TipocambioService,
              private router: Router,
              private activateRoute: ActivatedRoute) { }

  /** METODOS **/
  ngOnInit(): void {

    this.tipoCambioService.getDivisas().subscribe(
      divisas => {
        this.tiposCambioOrigen = divisas
        this.tiposCambioDestino = divisas
      }
    );
  }

  public createConsulta(): void{
    this.consultaService.createConsulta(this.consulta).subscribe(
      response => this.router.navigate(['/consultas'])
    );
    console.log("Consulta creada");
  }

  public updateConsulta(): void{
    console.log("Consulta actualizada");
  }

  compararTipoCambio(o1: Tipocambio, o2: Tipocambio): boolean{
    if(o1 === undefined && o2 === undefined){
      return true;
    }
    return o1 === null || o2 === null || o1 === undefined || o2 === undefined? false: o1.id === o2.id;
  }

}
