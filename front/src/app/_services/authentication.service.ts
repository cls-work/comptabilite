import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {UserModel} from '../_models/user.model';
import {ROLE_ADMIN, AUTH, BASE_URL, SIGN_IN, SIGN_UP, ROLE_USER} from '../_globals/vars';
// import * as jwt_decode from 'jwt-decode';


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<UserModel>;
  public currentUser: Observable<UserModel>;
  private roles = [ROLE_ADMIN, ROLE_USER];

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<UserModel>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): UserModel {
    return this.currentUserSubject.value;
  }

  login(usernameOrEmail: string, password: string) {
    // console.log(BASE_URL + AUTH + SIGN_IN);
    return this.http.post<any>(BASE_URL + AUTH + SIGN_IN, { usernameOrEmail, password })
      .pipe(map(data => {
        const user: UserModel = new UserModel();
        // login successful if there's a jwt token in the response
        if (data && data.accessToken && data.user) {
          user.id = data.user.id;
          user.name = data.user.name;
          user.username = data.user.username;
          user.email = data.user.email;
          user.accessToken = data.accessToken;
          user.role = data.user.roles[0].name;
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          // console.log(localStorage.getItem('currentUser'));

          // console.log(user)
          this.currentUserSubject.next(user);
        }

        return user;
      }));
  }

  addUser(user) {
    console.log(user)
    // return this.http.post(BASE_URL + AUTH + SIGN_UP, user);
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

}
