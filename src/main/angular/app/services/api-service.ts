import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { tokenFeatureSelector } from '../state/auth/auth.selector';

type ApiResponse<T> = {
    status: string;
    results: T;
};

@Injectable({ providedIn: 'root' })
export class ApiService {
    private readonly http = inject(HttpClient);
    private readonly apiBase = 'http://localhost:8080/api';
    private readonly store = inject(Store);

    protected token = this.store.selectSignal(tokenFeatureSelector);

    get<T>(path: string) {
        return this.http.get<ApiResponse<T>>(`${this.apiBase}/${path}`, {
            headers: {
                Authorization: this.token() ? `Bearer ${this.token()}` : '',
            },
        });
    }

    post<T>(path: string, body: any) {
        return this.http.post<ApiResponse<T>>(`${this.apiBase}/${path}`, body, {
            headers: {
                Authorization: this.token() ? `Bearer ${this.token()}` : '',
            },
        });
    }

    patch<T>(path: string, body: any) {
        return this.http.patch<ApiResponse<T>>(
            `${this.apiBase}/${path}`,
            body,
            {
                headers: {
                    Authorization: this.token() ? `Bearer ${this.token()}` : '',
                },
            },
        );
    }
}
