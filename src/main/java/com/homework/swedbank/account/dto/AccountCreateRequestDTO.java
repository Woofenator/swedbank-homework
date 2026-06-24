package com.homework.swedbank.account.dto;

import com.homework.swedbank.utils.currency_code.CurrencyCode;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountCreateRequestDTO {

    @NotBlank
    @CurrencyCode
    private String currencyCode;
}
