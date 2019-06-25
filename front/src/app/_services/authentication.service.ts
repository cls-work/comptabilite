import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {UserModel} from '../_models/user.model';
import {ROLE_ADMIN, AUTH, BASE_URL, SIGN_IN, SIGN_UP, ROLE_USER, USERS, LANG} from '../_globals/vars';
import {TranslateService} from './translate.service';
import {UserService} from './user.service';
// import * as jwt_decode from 'jwt-decode';


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<UserModel>;
  public currentUser: Observable<UserModel>;
  private roles = [ROLE_ADMIN, ROLE_USER];

  constructor(private http: HttpClient,
              private translateService: TranslateService) {
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
        console.log(data);
        // login successful if there's a jwt token in the response
        if (data && data.accessToken && data.user) {
          user.id = data.user.id;
          user.name = data.user.name;
          user.username = data.user.username;
          user.email = data.user.email;
          user.accessToken = data.accessToken;
          user.role = data.user.roles[0].name;
          user.lang = data.user.lang;
          localStorage.setItem('currentUser', JSON.stringify(user));
          console.log(JSON.parse(localStorage.getItem('currentUser')));
          if (user.lang) {
            this.translateService.use(user.lang);
            console.log('lang not null');
          } else {
            user.lang = localStorage.getItem('lang');
            this.setLang(localStorage.getItem('lang'), user.id)
              .subscribe(data => {
                console.log(data);
              });
            console.log('lang null');
          }
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          // console.log(localStorage.getItem('currentUser'));

          // console.log(user)
          this.currentUserSubject.next(user);
        }

        return user;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }

  setLang(lang: string, id: string) {
    return this.http.post(BASE_URL + USERS + LANG + id, {lang});
  }

}
