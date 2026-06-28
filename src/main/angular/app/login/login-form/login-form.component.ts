import { Component, signal } from '@angular/core';

import { form, FormField, required } from '@angular/forms/signals';
import { MatIconModule } from '@angular/material/icon';
import { MatInput, MatInputModule } from '@angular/material/input';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinner } from '@angular/material/progress-spinner';

interface LoginFormModel {
    username: string;
    password: string;
}

@Component({
    selector: 'app-login-form',
    templateUrl: './login-form.component.html',
    styleUrl: './login-form.component.css',
    imports: [
        MatInputModule,
        MatButtonModule,
        MatButton,
        MatInput,
        MatCardModule,
        FormField,
        MatIconModule,
        MatProgressSpinner,
    ],
})
export class LoginFormComponent {
    showProgress = signal<boolean>(false);
    registerModel = signal<LoginFormModel>({
        username: '',
        password: '',
    });

    registerForm = form(this.registerModel, (schemaPath) => {
        required(schemaPath.username, { message: 'Username is required' });

        required(schemaPath.password, { message: 'Password is required' });
    });

    onSubmit() {
        this.showProgress.set(true);
        alert('foo');
        console.log(this.registerForm);
    }

    passwordHide = signal(true);
    passwordClickEvent(event: MouseEvent) {
        this.passwordHide.set(!this.passwordHide());
        event.stopPropagation();
    }
}
