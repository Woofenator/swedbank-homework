package com.homework.swedbank.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.homework.swedbank.account.dto.AccountAddMoneyRequestDTO;
import com.homework.swedbank.account.dto.AccountCreateRequestDTO;
import com.homework.swedbank.account.dto.AccountResponseDTO;
import com.homework.swedbank.user.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    public List<AccountResponseDTO> getAccounts(User owner) {

        return accountRepository
                .getByOwner(owner)
                .stream()
                .map(AccountValueMapper::convertToDTO)
                .toList();
    }

    public AccountResponseDTO addBalance(String id, User owner, AccountAddMoneyRequestDTO requestDTO)
            throws NotFoundException {

        Optional<Account> foundAccount = accountRepository.getByIdAndOwner(id, owner);

        if (foundAccount.isEmpty()) {
            throw new NotFoundException(); // TODO Replace with specific Not Found exception
        }

        Account account = foundAccount.get();
        account.setBalance(account.getBalance() + requestDTO.getAmount());
        accountRepository.save(account);

        return AccountValueMapper.convertToDTO(account);
    }

    public AccountResponseDTO addAccount(AccountCreateRequestDTO request, User owner) {

        Account account = new Account();
        account.setBalance(0);
        account.setCurrencyCode(request.getCurrencyCode());
        account.setOwner(owner);
        accountRepository.save(account);

        return AccountValueMapper.convertToDTO(account);
    }
}
