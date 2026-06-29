package com.homework.swedbank.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.homework.swedbank.account.Account;

public interface TransactionRepisotory extends CrudRepository<Transaction, String> {

    Page<Transaction> findBySourceAccountOrderByTransactionDate(Account sourceAccount, Pageable pageable);
}
