import { createActionGroup, props } from '@ngrx/store';
import { Account } from './account.interface';

export const AccountActions = createActionGroup({
    source: 'Accounts',
    events: {
        setActive: props<{ accountId: string }>(),
    },
});

export const AccountApiActions = createActionGroup({
    source: 'Account API',
    events: {
        list: props<{ accounts: ReadonlyArray<Account> }>(),
        create: props<{ account: Readonly<Account> }>(),
        addBalance: props<{ account: Readonly<Account> }>(),
    },
});
