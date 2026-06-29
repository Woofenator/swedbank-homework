import { inject, Injectable } from '@angular/core';
import {
    ActivatedRouteSnapshot,
    CanActivate,
    GuardResult,
    MaybeAsync,
    Router,
    RouterStateSnapshot,
} from '@angular/router';
import { Store } from '@ngrx/store';
import { tokenFeatureSelector } from '../state/auth/auth.selector';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    private readonly store = inject(Store);
    private readonly router = inject(Router);
    protected token = this.store.selectSignal(tokenFeatureSelector);

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot,
    ): MaybeAsync<GuardResult> {
        if (this.token()) {
            return true;
        }

        this.router.navigate(['/login']);
        return false;
    }
}
