import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, ResolveFn } from '@angular/router';
import { Store } from '@ngrx/store';
import { TransactionActions } from '../state/transactions/transaction.actions';
import { Transaction } from '../state/transactions/transaction.interface';
import { selectActiveTransaction } from '../state/transactions/transaction.selector';

export const transactionResolver: ResolveFn<Transaction | undefined> = (
    route: ActivatedRouteSnapshot,
) => {
    const store = inject(Store);
    const transactionId = route.paramMap.get('transactionId') ?? '';

    store.dispatch(TransactionActions.setActive({ transactionId }));

    return store.select(selectActiveTransaction);
};
