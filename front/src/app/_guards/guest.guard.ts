import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import { Observable } from 'rxjs';
import {AuthenticationService} from '../_services/authentication.service';

@Injectable({
  providedIn: 'root'
})
// check if a user is logged in
export class GuestGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = this.authenticationService.currentUserValue;
    if (!currentUser) {
      // not logged in so return true
      return true;
    }

    // logged in so redirect to bills
    this.router.navigate(['/bills']);
    return false;
  }
}
