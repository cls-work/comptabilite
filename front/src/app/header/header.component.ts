import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../_services/authentication.service';
import {Router} from '@angular/router';
import {UserModel} from '../_models/user.model';
import {TranslateService} from '../_services/translate.service';
import {ROLE_ADMIN} from '../_globals/vars';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  currentUser: UserModel;
  Currentlangue = 'fr';

  constructor(private router: Router,
              private translateService: TranslateService,
              private authenticationService: AuthenticationService) {

    this.authenticationService.currentUser
      .subscribe(x => {
        this.currentUser = x;
      });
  }

  ngOnInit() {
  }

  changeLang(lang: string) {
    this.translateService.use(lang);
    if (this.currentUser) {
      this.authenticationService.setLang(lang, this.currentUser.id)
        .subscribe(() => {
        });
    }

  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  preventDefault($event: MouseEvent) {
    $event.preventDefault();
  }

  get adminRole() {
    return ROLE_ADMIN;
  }
}
