package com.homework.swedbank.currency;

import com.homework.swedbank.currency.dto.ConversionRateResponseDTO;

public class CurrencyValueMapper {

    public static ConversionRateResponseDTO toConversionRateDTO(CurrencyCode currencyCode, double conversionRate) {

        return ConversionRateResponseDTO.builder().currencyCode(currencyCode).conversionRate(conversionRate).build();
    }
}
