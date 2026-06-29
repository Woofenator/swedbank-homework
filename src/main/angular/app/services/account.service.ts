import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import {
    Account,
    AccountAddBalance,
    AccountCreate,
} from '../state/accounts/account.interface';
import { ApiService } from './api.service';

@Injectable({ providedIn: 'root' })
export class AccountService {
    private readonly client = inject(ApiService);
    private readonly basePath = 'accounts';

    getAccounts(): Observable<Array<Account>> {
        return this.client
            .get<Account[]>(this.basePath)
            .pipe(map(({ results }) => results || []));
    }

    addAccount(body: AccountCreate) {
        return this.client
            .post<Account>(this.basePath, body)
            .pipe(map(({ results }) => results || undefined));
    }

    addBalance(body: AccountAddBalance, accountId: string) {
        return this.client
            .post<Account>(`${this.basePath}/${accountId}`, body)
            .pipe(map(({ results }) => results || undefined));
    }
}
