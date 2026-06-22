package com.homework.swedbank.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.swedbank.auth.dto.LoginRequestDTO;
import com.homework.swedbank.auth.dto.LoginResponseDTO;
import com.homework.swedbank.auth.dto.RegisterRequestDTO;
import com.homework.swedbank.dto.APIResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    public static final String SUCCESS = "Success";
    private AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequestDTO entity) {

        authService.register(entity);
    }

    @PostMapping
    public ResponseEntity<APIResponse> login(@RequestBody @Valid LoginRequestDTO entity) {

        var authResponse = authService.authenticate(entity);

        var responseDTO = APIResponse.<LoginResponseDTO>builder().status(SUCCESS).results(authResponse).build();

        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

}
