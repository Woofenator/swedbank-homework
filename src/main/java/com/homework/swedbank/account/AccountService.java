package com.homework.swedbank.account;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public AccountResponseDTO addAccount(AccountCreateRequestDTO request, User owner) {

        Account account = new Account();
        account.setBalance(0);
        account.setCurrencyCode(request.getCurrencyCode());
        account.setOwner(owner);
        accountRepository.save(account);

        return AccountValueMapper.convertToDTO(account);
    }
}
