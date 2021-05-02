import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Pessoa} from '../../../core/models/Pessoa';
import * as moment from 'moment';
import 'moment/locale/pt-br';
import {ApiService} from '../../../services/api.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {

  maskAniversario = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/];
  maskCpf = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];

  pessoa: Pessoa = null;
  dataAniversario;

  constructor(public dialogRef: MatDialogRef<EditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Pessoa,
              private apiService: ApiService,
              private snackBar: MatSnackBar) {
    this.pessoa = data;
    this.dataAniversario = moment(this.pessoa.birthDate).format('DD/MM/YYYY');
  }

  ngOnInit(): void {

  }

  atualizar(): void {
    this.pessoa.birthDate = moment(this.dataAniversario, 'DD/MM/YYYY').toDate();
    this.pessoa.cpf = this.pessoa.cpf.replace(/[.-]/ig, '');
    this.apiService.updatePessoa(this.pessoa).then(res => {
      if (res.ok) {
        this.snackBar.open('Atualizado com sucesso!', 'Ok');
        this.dialogRef.close(this.pessoa);
      } else {
        this.snackBar.open('Erro ao atualizar cadastro!', 'Ok');
      }
    }).catch(err => {
      const errors = err.error.errors.map(o => o.field + '=' + o.message);
      alert(errors.join(', '));
    });
  }
}
