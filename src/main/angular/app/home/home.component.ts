import { Component, inject, OnInit, signal } from '@angular/core';
import { form, FormField, FormRoot } from '@angular/forms/signals';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AccountService } from '../services/account.service';
import { AccountApiActions } from '../state/accounts/account.actions';
import {
    Account,
    AccountCreate,
    currencyCodes,
} from '../state/accounts/account.interface';
import { selectAccounts } from '../state/accounts/account.selector';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrl: './home.component.css',
    imports: [
        MatGridListModule,
        MatMenuModule,
        MatIconModule,
        MatButtonModule,
        MatCardModule,
        MatSelectModule,
        MatProgressSpinner,
        FormRoot,
        FormField,
    ],
})
export class HomeComponent implements OnInit {
    private readonly router = inject(Router);
    private readonly accountService = inject(AccountService);
    private readonly store = inject(Store);

    accounts = this.store.selectSignal(selectAccounts);
    currencies = currencyCodes;

    accountCreateModel = signal<AccountCreate>({
        currencyCode: currencyCodes[0],
    });

    accountCreateForm = form(this.accountCreateModel, {
        submission: {
            action: async (formTree) => {
                await new Promise<void>((resolve, reject) => {
                    this.accountService
                        .addAccount(formTree().value())
                        .subscribe({
                            next: (account) => {
                                this.store.dispatch(
                                    AccountApiActions.create({ account }),
                                );
                                resolve();
                            },
                            error: reject,
                        });
                }).catch(() => ({
                    kind: 'serverError',
                    message: 'failed to submit',
                }));
            },
        },
    });

    ngOnInit(): void {
        this.accountService
            .getAccounts()
            .subscribe((accounts) =>
                this.store.dispatch(AccountApiActions.list({ accounts })),
            );
    }

    onViewAccount(account: Account) {
        this.router.navigate([`/account/${account.id}`]);
    }
}
