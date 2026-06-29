package com.homework.swedbank.transaction;

import com.homework.swedbank.transaction.dto.TransactionResponseDTO;

public class TransactionValueMapper {

    public static TransactionResponseDTO convertToDTO(Transaction transaction) {

        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .sourceAmount(transaction.getSourceAmount())
                .destinationAmount(transaction.getDestinationAmount())
                .conversionRate(transaction.getConversionRate())
                .sourceAccountId(transaction.getSourceAccount().getId())
                .destinationAccountId(transaction.getDestinationAccount().getId())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}
