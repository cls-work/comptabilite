import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {UserModel} from '../_models/user.model';
import {AUTH, BASE_URL, SIGN_IN, SIGN_UP} from '../_globals/vars';
// import * as jwt_decode from 'jwt-decode';


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<UserModel>;
  public currentUser: Observable<UserModel>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<UserModel>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): UserModel {
    return this.currentUserSubject.value;
  }

  login(usernameOrEmail: string, password: string) {
    return this.http.post<any>(BASE_URL + AUTH + SIGN_IN, { usernameOrEmail, password })
      .pipe(map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.accessToken) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
          console.log(localStorage.getItem('currentUser'));
          console.log(this.getDecodedAccessToken(JSON.parse(localStorage.getItem('currentUser')).accessToken))
          this.currentUserSubject.next(user);
        }

        return user;
      }));
  }

  addUser(user) {
    return this.http.post(BASE_URL + AUTH + SIGN_UP, user);
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  getDecodedAccessToken(token: string): any {
   /* try {
      return jwt_decode(token);
    } catch (Error) {
      return null;
    }*/
  }
}
