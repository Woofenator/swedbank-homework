package com.homework.swedbank.currency;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.homework.swedbank.currency.dto.ConversionRateResponseDTO;

@Service
public class CurrencyService {

    public Double getConversionRate(CurrencyCode sourceCurrency, CurrencyCode destinationCurrency) {

        Map<CurrencyCode, Double> conversionRateMap = ConversionRateMap.conversionRates.get(sourceCurrency);

        return conversionRateMap.get(destinationCurrency);
    }

    public List<ConversionRateResponseDTO> getAllConversionRates(CurrencyCode sourceCurrency) {

        Map<CurrencyCode, Double> conversionRateMap = ConversionRateMap.conversionRates.get(sourceCurrency);

        return conversionRateMap
                .entrySet()
                .stream()
                .map(entry -> CurrencyValueMapper.toConversionRateDTO(entry.getKey(), entry.getValue()))
                .toList();
    }
}
