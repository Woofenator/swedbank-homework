package com.homework.swedbank.transaction;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.homework.swedbank.account.Account;

public interface TransactionRepisotory extends CrudRepository<Transaction, String> {

    List<Transaction> findBySourceAccount(Account sourceAccount);
}
