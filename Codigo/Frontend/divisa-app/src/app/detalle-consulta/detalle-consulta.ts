import {Consulta} from "../consulta/consulta";

export class DetalleConsulta {
  id: number | undefined;
  monto: number | undefined;
  monto_devuelto: number | undefined;
  moneda_origen: string | undefined;
  moneda_destino: string | undefined;
  exchange_rate: number | undefined;
  consulta: Consulta = new Consulta();
}
