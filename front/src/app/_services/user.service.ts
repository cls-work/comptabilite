import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel} from '../_models/user.model';
import {BASE_URL, FORGOT_PASSWORD, NEW_PASSWORD, USERS, VERIFY} from '../_globals/vars';



@Injectable({ providedIn: 'root' })
export class UserService {
  constructor(private http: HttpClient) { }

  getAllUsers() {
    return this.http.get<UserModel[]>(BASE_URL + USERS);
  }

  getUserById(id) {
    return this.http.get<UserModel>(BASE_URL + USERS + id);
  }

  getToken(email: string) {
    return this.http.post(BASE_URL + FORGOT_PASSWORD, {email});
  }

  verifyToken(token: string) {
    return this.http.post(BASE_URL + FORGOT_PASSWORD + VERIFY, {token});
  }

  resetPassword(password, token) {
    console.log({password, token});
    return this.http.post(BASE_URL + FORGOT_PASSWORD + NEW_PASSWORD, {password, token});
  }
}
