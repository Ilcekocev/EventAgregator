import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {AuthService} from "../services/auth.service";
import 'rxjs/add/operator/map'

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private authService : AuthService,
    private router: Router){
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
     if (this.authService.validateAuthentication()) {
       return true;
     } else  {
       this.router.navigate(['login']);
       return false;
     }
  }
}
