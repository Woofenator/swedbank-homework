import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import {
    Transaction,
    TransactionCreate,
} from '../state/transactions/transaction.interface';
import { ApiService } from './api.service';

@Injectable({ providedIn: 'root' })
export class TransactionService {
    private readonly client = inject(ApiService);
    private readonly basePath = 'accounts';

    getTransactions(
        accountId: string,
        page: number = 0,
    ): Observable<Array<Transaction>> {
        return this.client
            .get<Transaction[]>(
                `${this.basePath}/${accountId}/transactions?page=${page}`,
            )
            .pipe(map(({ results }) => results || []));
    }

    addTransaction(body: TransactionCreate, accountId: string) {
        return this.client
            .post<Transaction>(
                `${this.basePath}/${accountId}/transactions`,
                body,
            )
            .pipe(map(({ results }) => results || undefined));
    }
}
