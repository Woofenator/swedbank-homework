import { inject, Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
    private readonly client = inject(ApiService);

    login(body: { username: string; password: string }): Observable<string> {
        return this.client
            .post<{ token: string; subject: string; expiresAt: number }>(
                `auth`,
                body,
            )
            .pipe(map(({ results }) => results.token));
    }

    register(body: {
        username: string;
        password: string;
        name: string;
        email: string;
    }): Observable<boolean> {
        return this.client.post<{}>('auth/register', body).pipe(
            map(({ status }) => {
                console.log(status);
                return status === 'Success';
            }),
        );
    }
}
