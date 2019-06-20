import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel} from '../_models/user.model';
import {BASE_URL, USERS} from '../_globals/vars';



@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get<UserModel[]>(BASE_URL + USERS);
  }

  getUserById(id) {
    return this.http.get<UserModel>(BASE_URL + USERS + id);
  }
}
