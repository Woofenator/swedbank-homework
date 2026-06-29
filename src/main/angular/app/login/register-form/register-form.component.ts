import { Component, inject, output, signal } from '@angular/core';

import {
    email,
    form,
    FormField,
    FormRoot,
    required,
    validate,
} from '@angular/forms/signals';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth-service';

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
    imports: [
        MatInputModule,
        MatButtonModule,
        MatCardModule,
        FormField,
        FormRoot,
        MatIconModule,
        MatProgressSpinner,
    ],
})
export class RegisterFormComponent {
    private readonly authService = inject(AuthService);
    registered = output<void>();
    registerModel = signal<RegisterFormModel>({
        username: '',
        password: '',
        confirmPassword: '',
        name: '',
        email: '',
    });

    showProgress = signal<boolean>(false);
    passwordHide = signal<boolean>(true);
    confirmPasswordHide = signal<boolean>(true);

    registerForm = form(
        this.registerModel,
        (schemaPath) => {
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
        },
        {
            submission: {
                action: async (formTree) => {
                    await new Promise<void>((resolve) => {
                        this.authService
                            .register(formTree().value())
                            .subscribe(() => {
                                resolve();
                            });
                    });
                    this.registered.emit();
                },
            },
        },
    );

    onSubmit() {
        console.log(this.registerForm);
    }

    passwordClickEvent(event: MouseEvent) {
        this.passwordHide.set(!this.passwordHide());
        event.stopPropagation();
    }

    confirmPasswordClickEvent(event: MouseEvent) {
        this.confirmPasswordHide.set(!this.confirmPasswordHide());
        event.stopPropagation();
    }
}
