import { Component, OnInit } from '@angular/core';
import {Usuario} from "../usuario/usuario";
import {Tipocambio} from "./tipocambio";
import {UsuarioService} from "../usuario/usuario.service";
import {TipocambioService} from "./tipocambio.service";
import {ActivatedRoute, Router} from "@angular/router";
import Swal from "sweetalert2";

@Component({
  selector: 'app-tipocambio',
  templateUrl: './tipocambio.component.html',
  styleUrls: ['./tipocambio.component.css']
})
export class TipocambioComponent implements OnInit {

  /** VARIABLES **/
  tiposCambio: Tipocambio[]=[];
  // tipoCambio: Tipocambio = new Tipocambio();
  tituloTipoCambio: string =  'Lista de Tipos de Cambio';

  constructor(private tipocambioService: TipocambioService) { }

  ngOnInit(): void {

    this.tipocambioService.getDivisas().subscribe(
      (tiposCambio) => {
        this.tiposCambio = tiposCambio
      }
    );
  }

  deleteTipoCambio(tipocambio: Tipocambio): void {


    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
      title: 'Está seguro?',
      text: `¿Seguro que desea eliminar el tipo de cambio ${tipocambio.divisa}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, Eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.tipocambioService.deleteDivisa(tipocambio.id).subscribe(
          response => {
            this.tiposCambio = this.tiposCambio.filter(tc => tc !== tipocambio)
            swalWithBootstrapButtons.fire(
              'Curso Eliminiado!',
              `El tipo de cambio ${tipocambio.divisa} ha sido eliminado con éxito.`,
              'success'
            )
          }
        )

      }
      // else if (
      //   /* Read more about handling dismissals below */
      //   result.dismiss === Swal.DismissReason.cancel
      // ) {
      //   swalWithBootstrapButtons.fire(
      //     'Cancelled',
      //     'Your imaginary file is safe :)',
      //     'error'
      //   )
      // }
    })

  }
}
