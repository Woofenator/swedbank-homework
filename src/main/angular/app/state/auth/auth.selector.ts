import { createFeatureSelector } from '@ngrx/store';

export const tokenFeatureSelector = createFeatureSelector<string>('auth');
