import { Component, inject, OnInit, signal } from '@angular/core';
import { form, FormField, FormRoot } from '@angular/forms/signals';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { provideNativeDateAdapter } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInput } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AccountService } from '../services/account.service';
import { TransactionService } from '../services/transaction.service';
import { AccountApiActions } from '../state/accounts/account.actions';
import { AccountAddBalance } from '../state/accounts/account.interface';
import { selectActiveAccount } from '../state/accounts/account.selector';
import { TransactionApiActions } from '../state/transactions/transaction.actions';
import { TransactionCreate } from '../state/transactions/transaction.interface';
import { selectTransactions } from '../state/transactions/transaction.selector';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrl: './account.component.css',
    imports: [
        MatGridListModule,
        MatMenuModule,
        MatIconModule,
        MatButtonModule,
        MatCardModule,
        MatSelectModule,
        MatProgressSpinner,
        MatDividerModule,
        MatTimepickerModule,
        MatDatepickerModule,
        FormRoot,
        FormField,
        MatInput,
    ],
    providers: [provideNativeDateAdapter()],
})
export class AccountComponent implements OnInit {
    private readonly router = inject(Router);
    private readonly accountService = inject(AccountService);
    private readonly transactionService = inject(TransactionService);
    private readonly store = inject(Store);
    transactions = this.store.selectSignal(selectTransactions);
    account = this.store.selectSignal(selectActiveAccount);

    transactionCreateModel = signal<TransactionCreate>({
        transactionDate: Date.now().toLocaleString(),
        amount: 0,
        destinationAccountId: '',
    });

    transactionCreateForm = form(this.transactionCreateModel, {
        submission: {
            action: async (formTree) => {
                await new Promise<void>((resolve, reject) => {
                    const account = this.account();
                    if (!account) {
                        reject();
                        return;
                    }
                    this.transactionService
                        .addTransaction(formTree().value(), account.id)
                        .subscribe({
                            next: (transaction) => {
                                this.store.dispatch(
                                    TransactionApiActions.create({
                                        transaction,
                                    }),
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

    accountAddBalanceModel = signal<AccountAddBalance>({
        amount: 0,
    });

    accountAddBalanceForm = form(this.accountAddBalanceModel, {
        submission: {
            action: async (formTree) => {
                await new Promise<void>((resolve, reject) => {
                    const account = this.account();
                    if (!account) {
                        reject();
                        return;
                    }
                    this.accountService
                        .addBalance(formTree().value(), account.id)
                        .subscribe({
                            next: (account) => {
                                console.log(account);
                                this.store.dispatch(
                                    AccountApiActions.addBalance({ account }),
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
        this.transactionService
            .getTransactions(this.account()!.id)
            .subscribe((transactions) =>
                this.store.dispatch(
                    TransactionApiActions.list({ transactions }),
                ),
            );
    }

    onBack() {
        this.router.navigate(['']);
    }

    onViewTransaction() {
        // this.router.navigate([`/${account.id}`]);
    }
}
