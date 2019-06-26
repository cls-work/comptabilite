import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserModel} from '../_models/user.model';
import {AUTH, BASE_URL, FORGOT_PASSWORD, LANG, NEW_PASSWORD, ROLES, SIGN_UP, USERS, VERIFY} from '../_globals/vars';



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

  // get reset password token from back
  getToken(email: string) {
    return this.http.post(BASE_URL + FORGOT_PASSWORD, {email});
  }

  // verify if reset token is still valid
  verifyToken(token: string) {
    return this.http.post(BASE_URL + FORGOT_PASSWORD + VERIFY, {token});
  }

  // push new passwords to back
  resetPassword(password, token) {
    return this.http.post(BASE_URL + FORGOT_PASSWORD + NEW_PASSWORD, {password, token});
  }

  deleteUser(idUser) {
    return this.http.delete(BASE_URL + USERS + idUser);
  }

  getAllRoles() {
    return this.http.get<any[]>(BASE_URL + USERS + ROLES);
  }

  addUser(user) {
    return this.http.post(BASE_URL + AUTH + SIGN_UP, user);
  }

  editUser(value: any, id: string) {
    return this.http.put(BASE_URL + USERS + id, value);
  }
}
