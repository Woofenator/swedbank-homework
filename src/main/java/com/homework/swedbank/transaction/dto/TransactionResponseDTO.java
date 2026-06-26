package com.homework.swedbank.transaction.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponseDTO {

    private String id;
    private LocalDate transactionDate;
    private int sourceAmount;
    private int destinationAmount;
    private double conversionRate;
    private String sourceAccountId;
    private String destinationAccountId;
}
