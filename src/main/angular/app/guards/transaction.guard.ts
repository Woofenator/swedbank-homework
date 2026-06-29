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
import { map } from 'rxjs';
import { selectActiveAccount } from '../state/accounts/account.selector';
import { selectTransactions } from '../state/transactions/transaction.selector';

@Injectable({ providedIn: 'root' })
export class TransactionGuard implements CanActivate {
    private readonly store = inject(Store);
    private readonly router = inject(Router);
    protected activeAccount = this.store.selectSignal(selectActiveAccount);

    canActivate(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot,
    ): MaybeAsync<GuardResult> {
        const transactionId = route.paramMap.get('transactionId');
        if (!transactionId) {
            this.router.navigate(['']);
            return false;
        }

        const transaction = this.store
            .select(selectTransactions)
            .pipe(
                map((transactions) =>
                    transactions.find((t) => t.id === transactionId),
                ),
            );
        if (!transaction) {
            this.router.navigate(['']);
            return false;
        }

        return true;
    }
}
