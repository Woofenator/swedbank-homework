package com.homework.swedbank.account;

import java.security.Principal;
import java.util.List;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.swedbank.account.dto.AccountCreateRequestDTO;
import com.homework.swedbank.account.dto.AccountAddMoneyRequestDTO;
import com.homework.swedbank.account.dto.AccountResponseDTO;
import com.homework.swedbank.dto.APIResponse;
import com.homework.swedbank.user.User;
import com.homework.swedbank.user.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
@Slf4j
public class AccountController {

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";
    private AccountService accountService;
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<APIResponse<List<AccountResponseDTO>>> getAccounts(Principal principal) {

        User currentUser = userService.getByUsername(principal.getName());
        var accountList = accountService.getAccounts(currentUser);

        var responseDTO = APIResponse.<List<AccountResponseDTO>>builder().status(SUCCESS).results(accountList).build();

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<APIResponse<AccountResponseDTO>> addAccount(
            @RequestBody @Valid AccountCreateRequestDTO request,
            Principal principal) {

        User currentUser = userService.getByUsername(principal.getName());
        var accountDTO = accountService.addAccount(request, currentUser);

        var responseDTO = APIResponse.<AccountResponseDTO>builder().status(SUCCESS).results(accountDTO).build();

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<APIResponse> addBalance(
            @PathVariable("id") String id,
            @RequestBody @Valid AccountAddMoneyRequestDTO request,
            Principal principal) {

        User currentUser = userService.getByUsername(principal.getName());
        try {

            AccountResponseDTO accountDTO = accountService.addBalance(id, currentUser, request);
            var responseDTO = APIResponse.builder().status(SUCCESS).results(accountDTO).build();

            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception ex) {

            log.error("Error adding balance: {}", ex.getMessage());
            return new ResponseEntity<>(APIResponse.builder().status(ERROR).build(), HttpStatus.NOT_FOUND);
        }
    }
}
