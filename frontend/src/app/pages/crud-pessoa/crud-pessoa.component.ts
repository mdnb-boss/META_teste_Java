import { Component, OnInit } from '@angular/core';
import {Pessoa} from '../../core/models/Pessoa';
import {ApiService} from '../../services/api.service';
import {MatDialog} from '@angular/material/dialog';
import {EditComponent} from './edit/edit.component';
import * as moment from 'moment';
import 'moment/locale/pt-br';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-crud-pessoa',
  templateUrl: './crud-pessoa.component.html',
  styleUrls: ['./crud-pessoa.component.scss']
})
export class CrudPessoaComponent implements OnInit {

  maskAniversario = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];
  maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];

  displayedColumns: string[] = ['id', 'name', 'cpf', 'createdAt', 'actions'];
  dataSource: Pessoa[] = [];

  pessoaInsert: Pessoa = new Pessoa();
  dataNascimento = null;

  constructor(private apiService: ApiService,
              public dialog: MatDialog,
              private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.loadListaPessoas();
  }

  loadListaPessoas(): void {
    this.apiService.getPessoas().then(pessoas => {
      this.dataSource = pessoas.data;
    });
  }

  cadastrar(): void {
    this.pessoaInsert.cpf = this.pessoaInsert.cpf.replace(/[.-]/ig, '');
    this.pessoaInsert.birthDate = moment(this.dataNascimento, 'DD/MM/YYYY').toDate();
    this.apiService.insertPessoa(this.pessoaInsert).then(res => {
      if (res.ok) {
        this.snackBar.open('Inserido com sucesso!', 'Ok');
        this.loadListaPessoas();
        this.pessoaInsert = new Pessoa();
        this.dataNascimento = null;
      } else {
        this.snackBar.open('Erro ao inserir cadastro!', 'Ok');
      }
    }).catch(err => {
      const errors = err.error.errors.map(o => o.field + '=' + o.message);
      alert(errors.join(', '));
    });
  }

  limpar(): void {
    this.pessoaInsert = new Pessoa();
  }

  edit(e: Pessoa): void {
    const dialogRef = this.dialog.open(EditComponent, { data: e });

    dialogRef.afterClosed().subscribe(result => {
      this.loadListaPessoas();
    });
  }

  delete(e: Pessoa): void {
    this.apiService.deletePessoa(e.id).then(() => {
      this.loadListaPessoas();
    });
  }
}
