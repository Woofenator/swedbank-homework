export interface Account {
    id: string;
    currencyCode: CurrencyCode;
    balance: number;
}

export interface AccountCreate {
    currencyCode: CurrencyCode;
}

export interface AccountAddBalance {
    amount: number;
}

export const currencyCodes = ['EUR', 'USD', 'SEK', 'GBP', 'VND'] as const;
export type CurrencyCode = (typeof currencyCodes)[number];
