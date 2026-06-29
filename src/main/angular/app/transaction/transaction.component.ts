import { Location } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MatAnchor } from '@angular/material/button';
import {
    MatCard,
    MatCardActions,
    MatCardContent,
    MatCardHeader,
    MatCardSubtitle,
    MatCardTitle,
} from '@angular/material/card';
import { Store } from '@ngrx/store';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { selectActiveTransaction } from '../state/transactions/transaction.selector';

@Component({
    selector: 'app-transaction',
    templateUrl: './transaction.component.html',
    styleUrl: './transaction.component.css',
    imports: [
        MatCardHeader,
        MatCard,
        MatCardSubtitle,
        MatCardContent,
        MatCardTitle,
        MatCardActions,
        MatAnchor,
    ],
})
export class TransactionComponent {
    private readonly store = inject(Store);
    private readonly location = inject(Location);
    transaction = this.store.selectSignal(selectActiveTransaction);

    onBack() {
        this.location.back();
    }

    generatePDF() {
        // The export is slightly jank, as it prints out a picture, not a proper pdf document
        // with formatting and everything
        const transaction = this.transaction();
        if (!transaction) {
            return;
        }
        const doc = new jsPDF();
        doc.setFontSize(16);
        doc.text(`Transaction ${transaction.id}`, 10, 10);
        doc.setFontSize(12);
        doc.text(
            `Destination Account: ${transaction.destinationAccountId}`,
            10,
            20,
        );
        const headers = [
            ['Date', 'Amount debited', 'Amount transfered', 'Conversion rate'],
        ];
        const data = [
            [
                new Date(transaction.transactionDate).toISOString(),
                transaction.sourceAmount,
                transaction.destinationAmount,
                transaction.conversionRate,
            ],
        ];
        autoTable(doc, { head: headers, body: data, startY: 30 });
        doc.save(`${transaction.id}.pdf`);
    }
}
