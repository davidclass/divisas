import { Component, OnInit } from '@angular/core';
import {Usuario} from "../usuario/usuario";
import {Tipocambio} from "./tipocambio";
import {UsuarioService} from "../usuario/usuario.service";
import {TipocambioService} from "./tipocambio.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-tipocambio',
  templateUrl: './tipocambio.component.html',
  styleUrls: ['./tipocambio.component.css']
})
export class TipocambioComponent implements OnInit {

  /** VARIABLES **/
  tiposCambio: Tipocambio[]=[];
  tipoCambio: Tipocambio = new Tipocambio();
  tituloTipoCambio: string =  'Lista de Tipos de Cambio';

  constructor(private tipocambioService: TipocambioService) { }

  ngOnInit(): void {

    this.tipocambioService.getDivisas().subscribe(
      (tiposCambio) => {
        this.tiposCambio = tiposCambio
      }
    );
  }

  deleteTipoCambio(tipocambio: Tipocambio) {

  }
}
