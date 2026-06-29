import { createReducer, on } from '@ngrx/store';
import { AuthApiActions } from './auth.actions';

export const initialState: string = '';

export const authReducer = createReducer(
    initialState,
    on(AuthApiActions.login, (_state, { token }) => token),
);
