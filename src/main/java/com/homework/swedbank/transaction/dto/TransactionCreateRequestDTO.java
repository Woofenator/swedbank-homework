package com.homework.swedbank.transaction.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionCreateRequestDTO {

    @Future
    private LocalDate transactionDate;

    private String destinationAccountId;

    @Positive
    private int amount;
}
