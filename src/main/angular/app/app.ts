import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterFormComponent } from './login/register-form/register-form.component';

@Component({
    selector: 'app-root',
    imports: [RouterOutlet, RegisterFormComponent],
    templateUrl: './app.html',
    styleUrl: './app.css',
})
export class App {
    protected readonly title = signal('client');
}
