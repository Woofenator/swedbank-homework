package com.homework.swedbank.account.dto;

import com.homework.swedbank.utils.currency_code.CurrencyCode;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequestDTO {

    @NotEmpty
    @CurrencyCode
    private String currencyCode;
}
