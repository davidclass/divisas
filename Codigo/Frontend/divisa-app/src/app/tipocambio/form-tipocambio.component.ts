import {Component, OnInit} from '@angular/core';
import {Consulta} from "../consulta/consulta";
import {Tipocambio} from "./tipocambio";
import {ActivatedRoute, Router} from "@angular/router";
import {TipocambioService} from "./tipocambio.service";

import Swal from "sweetalert2";

@Component({
  selector: 'app-form',
  templateUrl: './form-tipocambio.component.html',
  styleUrls: ['./form-tipocambio.component.css']
})
export class FormTipocambioComponent implements OnInit {

  /** VARIABLES **/
  public tituloFormTipoCambio: string = 'FORMULARIO TIPO CAMBIO';
  public tipoCambio: Tipocambio = new Tipocambio();

  constructor(private tipocambioService: TipocambioService,
              private router: Router,
              private activateRoute: ActivatedRoute) {
  }

  ngOnInit(): void {

    this.cargarTipoCambio();

  }

  cargarTipoCambio() {
    this.activateRoute.paramMap.subscribe(params => {
      let id = params.get('id');
      if (id) {
        this.tipocambioService.getDivisa(id).subscribe((tipoCambio) => this.tipoCambio = tipoCambio);
      }
    });
  }

  createTipoCambio(): void {

    this.tipocambioService.createDivisa(this.tipoCambio).subscribe(
      response => this.router.navigate(['/tipo-cambio'])
    );
    console.log("Divisa creada");

  }

  updateTipoCambio() {
    this.tipocambioService.updateDivisa(this.tipoCambio).subscribe(
      response => {
        this.router.navigate(['/tipo-cambio'])
        Swal.fire('Tipo Cambio Actualizado', `${this.tipoCambio.codigo}: ${this.tipoCambio.divisa}`, 'success');
      }
    );
  }

}
