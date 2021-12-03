import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { UsuarioService } from "./usuario/usuario.service";

import { RouterModule, Routes } from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { ConsultaComponent } from './consulta/consulta.component';
import {ConsultaService} from "./consulta/consulta.service";
import { FormConsultaComponent } from './consulta/form-consulta.component';

import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TipocambioComponent } from './tipocambio/tipocambio.component';
import { FormTipocambioComponent } from './tipocambio/form-tipocambio.component';
import {TipocambioService} from "./tipocambio/tipocambio.service";

const routes: Routes = [
  {path: '', redirectTo: '/usuarios', pathMatch: 'full'},
  {path: 'usuarios', component: UsuarioComponent},
  {path: 'consultas', component: ConsultaComponent},
  {path: 'consultas/form', component: FormConsultaComponent},
  {path: 'consultas/form/:id', component: FormConsultaComponent},
  {path: 'tipo-cambio', component: TipocambioComponent},
  {path: 'tipo-cambio/form', component: FormTipocambioComponent},
  {path: 'tipo-cambio/form/:id', component: FormTipocambioComponent}

];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    UsuarioComponent,
    ConsultaComponent,
    FormConsultaComponent,
    TipocambioComponent,
    FormTipocambioComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    UsuarioService,
    ConsultaService,
    TipocambioService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
