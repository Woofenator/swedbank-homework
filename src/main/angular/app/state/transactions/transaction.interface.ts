export interface TransactionCreate {
    transactionDate: string;
    destinationAccountId: string;
    amount: number;
}

export interface Transaction {
    id: string;
    transactionDate: Date;
    sourceAmount: number;
    destinationAmount: number;
    conversionRate: number;
    sourceAccountId: string;
    destinationAccountId: string;
}
