package com.homework.swedbank.transaction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.homework.swedbank.account.Account;
import com.homework.swedbank.account.AccountRepository;
import com.homework.swedbank.currency.CurrencyService;
import com.homework.swedbank.transaction.dto.TransactionCreateRequestDTO;
import com.homework.swedbank.transaction.dto.TransactionResponseDTO;
import com.homework.swedbank.transaction.mock.api.Weather;
import com.homework.swedbank.user.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private TransactionRepisotory transactionRepisotory;
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private CurrencyService currencyService;
    private static final RestClient restClient = RestClient.create("https://api.open-meteo.com");

    public List<TransactionResponseDTO> findBySourceAccount(String userId, String accountId) throws NotFoundException {

        Account sourceAccount = getSourceAccount(userId, accountId);
        List<Transaction> transactions = transactionRepisotory.findBySourceAccount(sourceAccount);

        return transactions.stream().map(TransactionValueMapper::convertToDTO).toList().reversed();
    }

    public TransactionResponseDTO createTransaction(
            String userId,
            String accountId,
            TransactionCreateRequestDTO request) throws NotFoundException {

        callExternal();

        Account sourceAccount = getSourceAccount(userId, accountId);
        Account destinationAccount = accountRepository.findById(request.getDestinationAccountId())
                .orElseThrow(NotFoundException::new);

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setSourceAmount(request.getAmount());
        transaction.setTransactionDate(request.getTransactionDate());

        if (sourceAccount.getCurrencyCode() != destinationAccount.getCurrencyCode()) {

            var conversionRate = currencyService.getConversionRate(sourceAccount.getCurrencyCode(),
                    destinationAccount.getCurrencyCode());
            transaction.setConversionRate(conversionRate);
            // Not sure how exactly rouding works in real life
            // So ceil to closest integer
            transaction.setDestinationAmount((int) Math.ceil(request.getAmount() * conversionRate));
        } else {

            transaction.setDestinationAmount(request.getAmount());
        }

        transactionRepisotory.save(transaction);
        sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getSourceAmount());
        destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getDestinationAmount());
        accountRepository.saveAll(List.of(sourceAccount, destinationAccount));

        return TransactionValueMapper.convertToDTO(transaction);
    }

    private Account getSourceAccount(String userId, String accountId) throws NotFoundException {
        var owner = userRepository.findById(userId);
        // Owner will be present due to Auth
        var sourceAccount = accountRepository.getByIdAndOwner(accountId, owner.get())
                .orElseThrow(NotFoundException::new);

        return sourceAccount;
    }

    private void callExternal() {

        // 56.92479576727988, 24.135628597303693
        // TV Tower in Riga
        var response = restClient.get().uri(
                "/v1/forecast?latitude=56.92479576727988&longitude=24.135628597303693&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,precipitation,weather_code")
                .retrieve().body(Weather.class);
        log.info(response.toString());
    }
}
