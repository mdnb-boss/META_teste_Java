import {ProfileGender} from '../enums/ProfileGender';

export class Pessoa {
  id: number;
  name: string;
  gender: ProfileGender;
  email: string;
  birthDate: Date;
  cpf: string;
  naturalness: string;
  nationality: string;
  createdAt: Date;
  updatedAt: Date;
}
