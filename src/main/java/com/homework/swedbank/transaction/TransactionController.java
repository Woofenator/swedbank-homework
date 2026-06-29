package com.homework.swedbank.transaction;

import java.security.Principal;
import java.util.List;

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

import com.homework.swedbank.dto.APIResponse;
import com.homework.swedbank.transaction.dto.TransactionCreateRequestDTO;
import com.homework.swedbank.transaction.dto.TransactionResponseDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts/{accountId}/transactions")
@AllArgsConstructor
public class TransactionController {

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";

    private TransactionService transactionService;

    @GetMapping("")
    public ResponseEntity<APIResponse> getTransactions(
            @PathVariable("accountId") String accountId,
            Principal principal) {
        try {
            List<TransactionResponseDTO> transactionList = transactionService.findBySourceAccount(principal.getName(),
                    accountId);
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

        try {
            TransactionResponseDTO transaction = transactionService.createTransaction(principal.getName(), accountId,
                    entity);
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
