package com.homework.swedbank.transaction;

import java.sql.Date;

import org.hibernate.annotations.Collate;

import com.homework.swedbank.account.Account;

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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "source_account_id", referencedColumnName = "id")
    private Account sourceAccount;
}
