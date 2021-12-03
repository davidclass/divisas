import {Usuario} from "../usuario/usuario";

export class Consulta {

  id: number | undefined;
  monto:number | undefined;
  moneda_origen: string | undefined;
  moneda_destino: string | undefined;
  usuario: Usuario = new Usuario();
}
