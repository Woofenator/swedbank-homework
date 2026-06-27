import { Component, input, model, ModelSignal } from '@angular/core';
import { FormValueControl } from '@angular/forms/signals';

@Component({
    templateUrl: './input-row.component.html',
    selector: 'input-row',
    imports: [],
})
export class InputRow implements FormValueControl<string> {
    value: ModelSignal<string> = model('');
    label = input.required<string>();
    type = input<string>('text');
    id = input<string>();
}
