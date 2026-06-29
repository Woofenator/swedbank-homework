import { Routes } from '@angular/router';
import { LoginPage } from './login/login-page';
import { AuthGuard } from './login/auth-guard';
import { App } from './app';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginPage,
        title: 'Login',
    },
    {
        path: '',
        component: App,
        canActivate: [AuthGuard],
    },
];
