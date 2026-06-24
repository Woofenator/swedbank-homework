package com.homework.swedbank.utils.currency_code;

import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrencyCodeValidator implements ConstraintValidator<CurrencyCode, String> {

    private static final Set<String> validCurrencies = Set.of("EUR", "USD", "SEK", "GBP", "VND");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return validCurrencies.contains(value);
    }
}
