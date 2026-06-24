package com.homework.swedbank.account;

import com.homework.swedbank.account.dto.AccountResponseDTO;

public class AccountValueMapper {

    public static AccountResponseDTO convertToDTO(Account account) {

        return AccountResponseDTO
                .builder()
                .id(account.getId())
                .balance(account.getBalance())
                .currencyCode(account.getCurrencyCode())
                .build();
    }
}
