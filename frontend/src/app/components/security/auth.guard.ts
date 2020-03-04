import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';


import { SharedService } from './../../services/shared.service';

@Injectable()
export class AuthGuard implements CanActivate {

    public shared: SharedService;

    constructor(private router: Router) {
        this.shared = SharedService.getInstance();
    }

    canActivate(
        router: ActivatedRouteSnapshot,
        state: RouterStateSnapshot): Observable<boolean> | boolean {

        if (this.shared.isLogged()) {
            return true;
        }
        this.router.navigate(['/login']);
        return false;
    }
}
