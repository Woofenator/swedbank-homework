package com.homework.swedbank.transaction.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.UUID;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCreateRequestDTO {

    @Future
    private LocalDateTime transactionDate;

    @UUID
    private String destinationAccountId;

    @Positive
    private int amount;
}
