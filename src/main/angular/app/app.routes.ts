import { Routes } from '@angular/router';
import { AccountComponent } from './account/account.component';
import { accountResolver } from './account/account.resolver';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './login/auth-guard';
import { LoginPage } from './login/login-page';

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
        path: ':accountId',
        component: AccountComponent,
        canActivate: [AuthGuard],
        resolve: {
            account: accountResolver,
        },
    },
];
