import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  /** VARIABLES **/
  tituloHeader:string = 'APP DIVISA';

  constructor() { }

  ngOnInit(): void {
  }

}
