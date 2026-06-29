import { createFeatureSelector, createSelector } from '@ngrx/store';

export const tokenFeatureSelector = createFeatureSelector<string>('auth');

export const selectToken = createSelector(tokenFeatureSelector, (token) => {
    return token;
});
