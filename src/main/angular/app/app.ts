import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterFormComponent } from './login/register-form/register-form.component';
import { LoginFormComponent } from './login/login-form/login-form.component';

@Component({
    selector: 'app-root',
    imports: [RouterOutlet, RegisterFormComponent, LoginFormComponent],
    templateUrl: './app.html',
    styleUrl: './app.css',
})
export class App {
    protected readonly title = signal('client');
}
