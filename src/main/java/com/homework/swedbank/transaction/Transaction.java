package com.homework.swedbank.transaction;

import java.time.LocalDate;

import com.homework.swedbank.account.Account;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRANSACTIONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Comparable<Transaction> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    private int sourceAmount;
    private int destinationAmount;

    // Only set for transactions where source and destination account
    // have different currency codes
    private @Nullable double conversionRate;

    @ManyToOne
    @JoinColumn(name = "source_account_id", referencedColumnName = "id")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id", referencedColumnName = "id")
    private Account destinationAccount;

    @Override
    public int compareTo(Transaction transaction) {

        return getTransactionDate().compareTo(transaction.getTransactionDate());
    }
}
