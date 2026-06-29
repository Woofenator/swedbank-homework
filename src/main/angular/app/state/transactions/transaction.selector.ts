import { createFeatureSelector, createSelector } from '@ngrx/store';
import { initialState } from './transaction.reducer';

const transactionStateSelector =
    createFeatureSelector<typeof initialState>('transactions');

export const selectTransactions = createSelector(
    transactionStateSelector,
    (state) => state.transactions,
);

export const selectActiveTransaction = createSelector(
    transactionStateSelector,
    (state) => state.active,
);
