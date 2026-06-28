import { Component, signal } from '@angular/core';

import {
    email,
    form,
    FormField,
    required,
    validate,
} from '@angular/forms/signals';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';

interface RegisterFormModel {
    username: string;
    password: string;
    confirmPassword: string;
    name: string;
    email: string;
}

@Component({
    selector: 'app-register-form',
    templateUrl: './register-form.component.html',
    styleUrl: './register-form.component.css',
    imports: [MatInputModule, MatButtonModule, MatCardModule, FormField],
})
export class RegisterFormComponent {
    registerModel = signal<RegisterFormModel>({
        username: '',
        password: '',
        confirmPassword: '',
        name: '',
        email: '',
    });

    registerForm = form(this.registerModel, (schemaPath) => {
        required(schemaPath.username, { message: 'Username is required' });

        required(schemaPath.email, { message: 'Email is required' });
        email(schemaPath.email, {
            message: 'Please enter a valid email address',
        });

        required(schemaPath.password, { message: 'Password is required' });

        required(schemaPath.name, { message: 'Name is required' });

        validate(schemaPath.confirmPassword, ({ value, valueOf }) => {
            const confirmPassword = value();
            const password = valueOf(schemaPath.password);

            if (confirmPassword !== password) {
                return {
                    kind: 'passwordMismatch',
                    message: 'Passwords do not match',
                };
            }

            return null;
        });
    });

    onSubmit() {
        console.log(this.registerForm);
    }
}
