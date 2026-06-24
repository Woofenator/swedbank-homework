package com.homework.swedbank.utils.currency_code;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = CurrencyCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyCode {

    String message() default "Invalid currency provided";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
