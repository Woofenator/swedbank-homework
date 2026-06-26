package com.homework.swedbank.transaction;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.swedbank.account.Account;
import com.homework.swedbank.account.AccountService;
import com.homework.swedbank.currency.CurrencyService;
import com.homework.swedbank.dto.APIResponse;
import com.homework.swedbank.transaction.dto.TransactionCreateRequestDTO;
import com.homework.swedbank.transaction.dto.TransactionResponseDTO;
import com.homework.swedbank.user.User;
import com.homework.swedbank.user.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts/{accountId}/transactions")
@AllArgsConstructor
public class TransactionController {

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";

    private TransactionService transactionService;
    private AccountService accountService;
    private UserService userService;
    private CurrencyService currencyService;

    @GetMapping("")
    public ResponseEntity<APIResponse> getTransactions(
            @PathVariable("accountId") String accountId,
            Principal principal) {

        User currentUser = userService.getByUsername(principal.getName());
        try {
            Account account = accountService.getByIdAndOwner(accountId, currentUser);

            List<TransactionResponseDTO> transactionList = transactionService.findBySourceAccount(account);
            var responseDTO = APIResponse.<List<TransactionResponseDTO>>builder().status(SUCCESS)
                    .results(transactionList).build();

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (NotFoundException _) {

            return new ResponseEntity<>(APIResponse.builder().status(ERROR).build(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<APIResponse> createTransaction(
            @PathVariable("accountId") String accountId,
            @RequestBody @Validated TransactionCreateRequestDTO entity,
            Principal principal) {

        User currentUser = userService.getByUsername(principal.getName());
        try {
            Account sourceAccount = accountService.getByIdAndOwner(accountId, currentUser);
            Account destinationAccount = accountService.getById(entity.getDestinationAccountId());
            Optional<Double> conversionRate = null;

            if (sourceAccount.getCurrencyCode() != destinationAccount.getCurrencyCode()) {
                conversionRate = Optional.of(currencyService.getConversionRate(sourceAccount.getCurrencyCode(),
                        destinationAccount.getCurrencyCode()));
            }

            TransactionResponseDTO transaction = transactionService.createTransaction(entity, sourceAccount,
                    destinationAccount, conversionRate);
            var responseDTO = APIResponse.<TransactionResponseDTO>builder().status(SUCCESS).results(transaction)
                    .build();

            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (NotFoundException _) {

            // TODO: Differentiate between not found source and not found destination
            // account
            return new ResponseEntity<>(APIResponse.builder().status(ERROR).build(), HttpStatus.NOT_FOUND);
        }
    }

}
