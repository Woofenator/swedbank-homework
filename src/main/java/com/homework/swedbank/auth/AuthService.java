package com.homework.swedbank.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.homework.swedbank.auth.dto.LoginRequestDTO;
import com.homework.swedbank.auth.dto.LoginResponseDTO;
import com.homework.swedbank.auth.dto.RegisterRequestDTO;
import com.homework.swedbank.user.User;
import com.homework.swedbank.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    AuthService(UserRepository userRepository, AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService) {

        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    public LoginResponseDTO authenticate(LoginRequestDTO loginDTO) {

        var token = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication latestResult = authenticationManager.authenticate(token);

        String jwtToken = jwtTokenService.generateToken(latestResult);
        Long expiresAt = jwtTokenService.extractExpirationTime(jwtToken);

        return new LoginResponseDTO(jwtToken, latestResult.getName(), expiresAt);
    }

    public void register(RegisterRequestDTO registerDTO) {

        try {

            User user = new User();
            user.setEmail(registerDTO.getEmail());
            user.setName(registerDTO.getName());
            user.setUsername(registerDTO.getUsername());
            user.setPasswordHash(encoder.encode(registerDTO.getPassword()));
            userRepository.save(user);
        } catch (Exception ex) {

            log.error("Could not register, Exception {}", ex.getMessage());
        }
    }

    public String login(LoginRequestDTO loginDTO) {

        try {

            Optional<User> foundUser = userRepository.findByUsername(loginDTO.getUsername());

            if (foundUser.isEmpty()) {

                return "";
            }

            if (!encoder.matches(loginDTO.getPassword(), foundUser.get().getPasswordHash())) {

                return "";
            }

        } catch (Exception ex) {

            log.error("Exception occured while logging in, Exception message {}", ex.getMessage());
        }

        return "success"; // TODO: Create session
    }
}
