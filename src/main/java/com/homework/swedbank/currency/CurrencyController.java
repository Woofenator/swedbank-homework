package com.homework.swedbank.currency;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homework.swedbank.currency.dto.ConversionRateResponseDTO;
import com.homework.swedbank.dto.APIResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/currency")
@AllArgsConstructor
@Slf4j
public class CurrencyController {

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";

    private CurrencyService currencyService;

    @GetMapping("/{currencyCode}")
    public ResponseEntity<APIResponse> getConversionRates(@PathVariable("currencyCode") String currencyCode) {

        try {
            CurrencyCode code = CurrencyCode.valueOf(currencyCode);

            var response = currencyService.getAllConversionRates(code);
            var apiResponse = APIResponse.<List<ConversionRateResponseDTO>>builder().status(SUCCESS).results(response)
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (IllegalArgumentException _) {
            log.info("Invalid currency code specified");

            return new ResponseEntity<>(APIResponse.builder().status(ERROR).build(), HttpStatus.NOT_FOUND);
        }
    }
}
