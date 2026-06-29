import { createReducer, on } from '@ngrx/store';
import {
    TransactionActions,
    TransactionApiActions,
} from './transaction.actions';
import { Transaction } from './transaction.interface';

export const initialState: {
    transactions: ReadonlyArray<Transaction>;
    active?: Transaction;
} = {
    transactions: [],
};

export const transactionReducer = createReducer(
    initialState,
    on(TransactionApiActions.create, (state, { transaction }) => {
        return {
            transactions: [...state.transactions, transaction],
            active: state.active,
        };
    }),
    on(TransactionApiActions.list, (state, { transactions }) => {
        return {
            transactions,
            active: state.active,
        };
    }),
    on(TransactionApiActions.addToList, (state, { transactions }) => {
        const idSet = new Set<string>();

        return {
            transactions: [...state.transactions, ...transactions].filter(
                (transaction) => {
                    if (idSet.has(transaction.id)) {
                        return false;
                    }
                    idSet.add(transaction.id);
                    return true;
                },
            ),
            active: state.active,
        };
    }),
    on(TransactionActions.setActive, (state, { transactionId }) => {
        return {
            transactions: state.transactions,
            active: state.transactions.find(
                (transaction) => transaction.id === transactionId,
            ),
        };
    }),
);
