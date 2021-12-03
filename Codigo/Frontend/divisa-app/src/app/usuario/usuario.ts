export class Usuario {

  id: number | undefined;
  username: string | undefined;
  password: string | undefined;
  enable: boolean | undefined;
  authorities:  string[] = [];
}
