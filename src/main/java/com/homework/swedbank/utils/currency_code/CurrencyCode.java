package com.homework.swedbank.utils.currency_code;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = CurrencyCode.CurrencyCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyCode {

    String message() default "Invalid currency provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CurrencyCodeValidator implements ConstraintValidator<CurrencyCode, String> {

        private static final Set<String> validCurrencies = Set.of("EUR", "USD", "SEK", "GBP", "VND");

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {

            return validCurrencies.contains(value);
        }
    }
}
