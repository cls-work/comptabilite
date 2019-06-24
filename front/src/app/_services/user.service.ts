import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel} from '../_models/user.model';
import {BASE_URL, FORGOT_PASSWORD, NEW_PASSWORD, ROLES, USERS, VERIFY} from '../_globals/vars';



@Injectable({ providedIn: 'root' })
export class UserService {

  roles: any[];
  constructor(private http: HttpClient) {
    this.getAllRoles().subscribe(roles => {
      this.roles = roles;
    });
  }

  getAllUsers() {
    return this.http.get<UserModel[]>(BASE_URL + USERS);
  }

  getUserById(idUser) {
    return this.http.get<UserModel>(BASE_URL + USERS + idUser);
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

  deleteUser(idUser) {
    return this.http.delete(BASE_URL + USERS + idUser);
  }

  getAllRoles() {
    return this.http.get<any[]>(BASE_URL + USERS + ROLES);
  }


}
