package com.homework.swedbank.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.homework.swedbank.user.User;

public interface AccountRepository extends CrudRepository<Account, String> {

    List<Account> getByOwner(User owner);

    Optional<Account> getByIdAndOwner(String id, User owner);
}
