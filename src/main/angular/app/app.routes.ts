import { Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { accountResolver } from './account/account.resolver';
import { AuthGuard } from './guards/auth.guard';
import { TransactionGuard } from './guards/transaction.guard';
import { HomeComponent } from './home/home.component';
import { LoginPage } from './login/login-page';
import { TransactionComponent } from './transaction/transaction.component';
import { transactionResolver } from './transaction/transaction.resolver';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginPage,
        title: 'Login',
    },
    {
        path: '',
        component: HomeComponent,
        canActivate: [AuthGuard],
    },
    {
        path: 'account/:accountId',
        component: AccountComponent,
        canActivate: [AuthGuard],
        resolve: {
            account: accountResolver,
        },
    },
    {
        path: 'transaction/:transactionId',
        component: TransactionComponent,
        canActivate: [AuthGuard, TransactionGuard],
        resolve: {
            transaction: transactionResolver,
        },
    },
];
