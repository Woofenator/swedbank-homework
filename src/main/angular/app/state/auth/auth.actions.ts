import { createActionGroup, props } from '@ngrx/store';

export const AuthApiActions = createActionGroup({
    source: 'Auth API',
    events: {
        login: props<{ token: string }>(),
    },
});
