package com.homework.swedbank.account.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountAddMoneyRequestDTO {

    @DecimalMin(value = "0", inclusive = true)
    @DecimalMax(value = Integer.MAX_VALUE + "", inclusive = false)
    private int amount;
}
