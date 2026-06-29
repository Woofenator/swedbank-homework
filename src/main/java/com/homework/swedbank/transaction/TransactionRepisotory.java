package com.homework.swedbank.transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.homework.swedbank.account.Account;

public interface TransactionRepisotory extends CrudRepository<Transaction, String> {

    List<Transaction> findBySourceAccount(Account sourceAccount);
}
