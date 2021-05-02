import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CrudPessoaComponent} from './pages/crud-pessoa/crud-pessoa.component';

const routes: Routes = [
  {
    path: '',
    component: CrudPessoaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
