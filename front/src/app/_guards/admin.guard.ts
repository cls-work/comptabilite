import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthenticationService} from '../_services/authentication.service';
import {ROLE_ADMIN} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})

// check if user is admin
export class AdminGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    if (currentUser.role === ROLE_ADMIN) {
      // is admin so return true
      return true;
    }

    // not admin so redirect to bills
    this.router.navigate(['/bills']);
    return false;
  }
}
