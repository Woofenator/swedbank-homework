import { Component, inject, output, signal } from '@angular/core';

import { form, FormField, FormRoot, required } from '@angular/forms/signals';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatInput, MatInputModule } from '@angular/material/input';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { Store } from '@ngrx/store';
import { AuthService } from '../../services/auth.service';
import { AuthApiActions } from '../../state/auth/auth.actions';
import { tokenFeatureSelector } from '../../state/auth/auth.selector';

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
        FormRoot,
        MatIconModule,
        MatProgressSpinner,
    ],
})
export class LoginFormComponent {
    private readonly authService = inject(AuthService);
    private readonly store = inject(Store);
    protected token = this.store.selectSignal(tokenFeatureSelector);
    loggedIn = output<void>();

    loginModel = signal<LoginFormModel>({
        username: '',
        password: '',
    });

    loginForm = form(
        this.loginModel,
        (schemaPath) => {
            required(schemaPath.username, { message: 'Username is required' });

            required(schemaPath.password, { message: 'Password is required' });
        },
        {
            submission: {
                action: async (formTree) => {
                    await new Promise<void>((resolve) => {
                        this.authService
                            .login(formTree().value())
                            .subscribe((token) => {
                                this.store.dispatch(
                                    AuthApiActions.login({ token }),
                                );
                                resolve();
                            });
                    });
                    this.loggedIn.emit();
                },
            },
        },
    );

    passwordHide = signal(true);
    passwordClickEvent(event: MouseEvent) {
        this.passwordHide.set(!this.passwordHide());
        event.stopPropagation();
    }
}
