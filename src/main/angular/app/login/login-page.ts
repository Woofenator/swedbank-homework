import { Component, inject, signal } from '@angular/core';
import { MatTabsModule } from '@angular/material/tabs';
import { Router } from '@angular/router';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';

@Component({
    templateUrl: './login-page.html',
    selector: 'login-page',
    imports: [MatTabsModule, LoginFormComponent, RegisterFormComponent],
})
export class LoginPage {
    activeTab = signal<number>(0);
    private readonly router = inject(Router);

    onRegistered() {
        this.activeTab.set(0);
    }

    onLogin() {
        this.router.navigate(['']);
    }
}
