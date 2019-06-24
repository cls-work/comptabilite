import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../_services/authentication.service';
import {Router} from '@angular/router';
import {UserModel} from '../_models/user.model';
import {TranslateService} from '../_services/translate.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentUser: UserModel;
  constructor(private router: Router,
              private translateService: TranslateService,
              private authenticationService: AuthenticationService) {

    this.authenticationService.currentUser
      .subscribe(x => {
        this.currentUser = x;
      });
  }

  ngOnInit() {
    console.log(this.translateService.data);
    console.log(this.currentUser);
  }

  changeLang(lang: string) {
    this.translateService.use(lang);
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
