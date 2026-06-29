import { createReducer, on } from '@ngrx/store';
import { AccountActions, AccountApiActions } from './account.actions';
import { Account } from './account.interface';

export const initialState: {
    accounts: ReadonlyArray<Account>;
    active?: Account;
} = { accounts: [] };

export const accountReducer = createReducer(
    initialState,
    on(AccountApiActions.list, (state, { accounts }) => {
        return {
            accounts: accounts,
            active: state.active,
        };
    }),
    on(AccountApiActions.create, (state, { account }) => {
        return {
            accounts: [...state.accounts, account],
            active: state.active,
        };
    }),
    on(AccountApiActions.addBalance, (state, { account }) => {
        let active = state.active;

        if (active?.id === account.id) {
            active = account;
        }

        return {
            accounts: state.accounts.map((stateAccount) => {
                if (stateAccount.id === account.id) {
                    return account;
                }

                return stateAccount;
            }),
            active,
        };
    }),
    on(AccountActions.setActive, (state, { accountId }) => {
        return {
            accounts: state.accounts,
            active: state.accounts.find((account) => account.id === accountId),
        };
    }),
);
