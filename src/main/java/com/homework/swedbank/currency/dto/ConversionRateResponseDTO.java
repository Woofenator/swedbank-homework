package com.homework.swedbank.currency.dto;

import com.homework.swedbank.currency.CurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversionRateResponseDTO {

    private CurrencyCode currencyCode;
    private double conversionRate;
}
