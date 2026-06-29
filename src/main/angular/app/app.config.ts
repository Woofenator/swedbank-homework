import {
    ApplicationConfig,
    provideBrowserGlobalErrorListeners,
} from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';

import { provideHttpClient, withFetch } from '@angular/common/http';
import { provideStore } from '@ngrx/store';
import { routes } from './app.routes';
import { accountReducer } from './state/accounts/account.reducer';
import { authReducer } from './state/auth/auth.reducer';
import { transactionReducer } from './state/transactions/transaction.reducer';

export const appConfig: ApplicationConfig = {
    providers: [
        provideBrowserGlobalErrorListeners(),
        provideRouter(routes, withComponentInputBinding()),
        provideHttpClient(withFetch()),
        provideStore({
            auth: authReducer,
            accounts: accountReducer,
            transactions: transactionReducer,
        }),
    ],
};
