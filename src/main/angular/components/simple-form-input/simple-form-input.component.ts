import { Component, input, model, ModelSignal } from '@angular/core';
import {
    FormValueControl,
    ValidationError,
    WithOptionalFieldTree,
} from '@angular/forms/signals';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldControl } from '@angular/material/form-field';

@Component({
    selector: 'simple-form-input',
    templateUrl: './simple-form-input.component.html',
    imports: [MatInputModule],
    providers: [
        { provide: MatFormFieldControl, useExisting: SimpleFormComponent },
    ],
})
export class SimpleFormComponent implements FormValueControl<string> {
    value: ModelSignal<string> = model('');
    invalid = input<boolean>(false);
    errors = input<readonly WithOptionalFieldTree<ValidationError>[]>([]);
    required = input<boolean>(false);
    label = input.required<string>();
    placeholder = input.required<string>();
}
