import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Pessoa} from '../core/models/Pessoa';
import {OkResponse} from '../core/models/OkResponse';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getPessoas(): Promise<OkResponse<Array<Pessoa>>> {
    return this.http.get<OkResponse<Array<Pessoa>>>(`${environment.API_URL}/api/user`, {
      headers: {
        Authorization: environment.API_TOKEN,
        'X-API-VERSION': '1',
      }
    }).toPromise();
  }

  insertPessoa(pessoa: Pessoa): Promise<OkResponse<Pessoa>> {
    return this.http.post<OkResponse<Pessoa>>(`${environment.API_URL}/api/user`, pessoa,
      {
        headers: {
          Authorization: environment.API_TOKEN,
          'X-API-VERSION': '1',
        }
      }).toPromise();
  }

  updatePessoa(pessoa: Pessoa): Promise<OkResponse<Pessoa>> {
    return this.http.put<OkResponse<Pessoa>>(`${environment.API_URL}/api/user/${pessoa.id}`, pessoa,
      {
      headers: {
        Authorization: environment.API_TOKEN,
        'X-API-VERSION': '1',
      }
    }).toPromise();
  }

  deletePessoa(id: number): Promise<OkResponse<any>> {
    return this.http.delete<OkResponse<any>>(`${environment.API_URL}/api/user/${id}`, {
      headers: {
        Authorization: environment.API_TOKEN,
        'X-API-VERSION': '1',
      }
    }).toPromise();
  }
}
