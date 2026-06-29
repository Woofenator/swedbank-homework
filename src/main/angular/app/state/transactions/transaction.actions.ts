import { createActionGroup, props } from '@ngrx/store';
import { Transaction } from './transaction.interface';

export const TransactionActions = createActionGroup({
    source: 'Transactions',
    events: {
        setActive: props<{ transactionId: string }>(),
    },
});

export const TransactionApiActions = createActionGroup({
    source: 'Transaction API',
    events: {
        list: props<{ transactions: ReadonlyArray<Transaction> }>(),
        create: props<{ transaction: Readonly<Transaction> }>(),
    },
});
