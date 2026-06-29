import { createFeatureSelector, createSelector } from '@ngrx/store';
import { initialState } from './account.reducer';

const accountStateSelector =
    createFeatureSelector<typeof initialState>('accounts');

export const selectAccounts = createSelector(
    accountStateSelector,
    (state) => state.accounts,
);

export const selectActiveAccount = createSelector(
    accountStateSelector,
    (state) => state.active,
);
