import { Component, signal } from '@angular/core';
import { form, FormField } from '@angular/forms/signals';
import { InputRow } from '../components/input-row.component';

interface LoginData {
    username: string;
    password: string;
    password_repeat: string;
    email: string;
    name: string;
}

@Component({
    selector: 'login-form',
    templateUrl: './login-form.html',
    imports: [FormField, InputRow],
})
export class LoginForm {
    loginModel = signal<LoginData>({
        username: '',
        password: '',
        password_repeat: '',
        email: '',
        name: '',
    });

    loginForm = form(this.loginModel);
}
