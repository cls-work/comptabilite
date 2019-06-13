import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../_models/user.model';
import {BASE_URL} from '../_globals/vars';



@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<User[]>(BASE_URL+'/users');
  }
}
