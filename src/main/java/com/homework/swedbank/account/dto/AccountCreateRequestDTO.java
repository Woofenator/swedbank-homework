package com.homework.swedbank.account.dto;

import com.homework.swedbank.currency.CurrencyCode;
import com.homework.swedbank.utils.currency_code.ValidCurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateRequestDTO {

    @ValidCurrencyCode
    private CurrencyCode currencyCode;
}
