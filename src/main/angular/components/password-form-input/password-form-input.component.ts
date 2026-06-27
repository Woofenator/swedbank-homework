import { Component } from '@angular/core';
import { SimpleFormComponent } from '../simple-form-input/simple-form-input.component';
import { ɵInternalFormsSharedModule } from '@angular/forms';
import { MatInputModule, MatFormField } from '@angular/material/input';

@Component({
    selector: 'password-form-input',
    templateUrl: './password-form-input.component.html',
    imports: [MatInputModule, ɵInternalFormsSharedModule, MatFormField],
})
export class PasswordFormInput extends SimpleFormComponent {}
