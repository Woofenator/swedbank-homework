package com.homework.swedbank.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.homework.swedbank.account.Account;
import com.homework.swedbank.transaction.dto.TransactionCreateRequestDTO;
import com.homework.swedbank.transaction.dto.TransactionResponseDTO;
import com.homework.swedbank.transaction.mock.api.Weather;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private TransactionRepisotory transactionRepisotory;
    private static final RestClient restClient = RestClient.create("https://api.open-meteo.com");

    public List<TransactionResponseDTO> findBySourceAccount(Account sourceAccount) {

        List<Transaction> transactions = transactionRepisotory.findBySourceAccount(sourceAccount);

        return transactions.stream().map(TransactionValueMapper::convertToDTO).toList().reversed();
    }

    public TransactionResponseDTO createTransaction(
            TransactionCreateRequestDTO request,
            Account sourceAccount,
            Account destinationAccount,
            Optional<Double> conversionRate) {

        // 56.92479576727988, 24.135628597303693
        // TV Tower in Riga
        var response = restClient.get().uri(
                "/v1/forecast?latitude=56.92479576727988&longitude=24.135628597303693&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,precipitation,weather_code")
                .retrieve().body(Weather.class);
        log.info(response.toString());

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setSourceAmount(request.getAmount());
        transaction.setTransactionDate(request.getTransactionDate());

        if (conversionRate.isPresent()) {

            transaction.setConversionRate(conversionRate.get());
            // Not sure how exactly rouding works in real life
            // So ceil to closest integer
            transaction.setDestinationAmount((int) Math.ceil(request.getAmount() * conversionRate.get()));
        } else {

            transaction.setDestinationAmount(request.getAmount());
        }

        return TransactionValueMapper.convertToDTO(transaction);
    }
}
