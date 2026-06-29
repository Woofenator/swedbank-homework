package com.homework.swedbank.utils.currency_code;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.homework.swedbank.currency.CurrencyCode;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidCurrencyCode.CurrencyCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrencyCode {

    String message() default "Invalid currency provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class CurrencyCodeValidator implements ConstraintValidator<ValidCurrencyCode, Enum<?>> {

        @Override
        public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {

            if (value == null) {
                return false;
            }

            try {

                CurrencyCode.valueOf(value.name());

                return true;
            } catch (IllegalArgumentException _) {

                return false;
            }
        }
    }
}
