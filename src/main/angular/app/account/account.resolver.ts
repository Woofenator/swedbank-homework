import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn } from '@angular/router';
import { Store } from '@ngrx/store';
import { AccountActions } from '../state/accounts/account.actions';
import { Account } from '../state/accounts/account.interface';
import { selectActiveAccount } from '../state/accounts/account.selector';

export const accountResolver: ResolveFn<Account | undefined> = (
    route: ActivatedRouteSnapshot,
) => {
    const store = inject(Store);
    const accountId = route.paramMap.get('accountId') ?? '';

    store.dispatch(AccountActions.setActive({ accountId }));

    return store.select(selectActiveAccount);
};
