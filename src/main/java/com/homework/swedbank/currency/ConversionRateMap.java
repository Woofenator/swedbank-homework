package com.homework.swedbank.currency;

import java.util.Map;

/**
 * Having a map of each currency to each other makes lookup easier
 * than trying to calculate inverse currency conversion rate if a
 * direct one doesn't exist
 * ConversionRateMap
 */
public class ConversionRateMap {
        public static final Map<CurrencyCode, Map<CurrencyCode, Double>> conversionRates = Map.of(
                        CurrencyCode.EUR, Map.of(
                                        CurrencyCode.USD, 1.14173703,
                                        CurrencyCode.SEK, 11.07212927,
                                        CurrencyCode.GBP, 0.86337972,
                                        CurrencyCode.VND, 30029.48),
                        CurrencyCode.USD, Map.of(
                                        CurrencyCode.EUR, 0.87590322,
                                        CurrencyCode.SEK, 9.69981106,
                                        CurrencyCode.GBP, 0.75616045,
                                        CurrencyCode.VND, 26293.87),
                        CurrencyCode.SEK, Map.of(
                                        CurrencyCode.EUR, 0.09029249,
                                        CurrencyCode.USD, 0.1030835,
                                        CurrencyCode.GBP, 0.07795031,
                                        CurrencyCode.VND, 2710.5716),
                        CurrencyCode.GBP, Map.of(
                                        CurrencyCode.EUR, 1.15834694,
                                        CurrencyCode.USD, 1.32240509,
                                        CurrencyCode.SEK, 12.83024994,
                                        CurrencyCode.VND, 34777.31),
                        CurrencyCode.VND, Map.of(
                                        CurrencyCode.EUR, 0.00003330,
                                        CurrencyCode.USD, 0.00003802,
                                        CurrencyCode.GBP, 0.00036892,
                                        CurrencyCode.SEK, 0.00002875));
}

/**
 * Currency Exchange rates at time of writing:
 * EUR -> USD = 1.14173703
 * EUR -> SEK = 11.07212927
 * EUR -> GBP = 0.86337972
 * EUR -> VND = 30,029.48
 * 
 * USD -> EUR = 0.87590322
 * USD -> SEK = 9.69981106
 * USD -> GBP = 0.75616045
 * USD -> VND = 26,293.87
 * 
 * SEK -> EUR = 0.09029249
 * SEK -> USD = 0.1030835
 * SEK -> GBP = 0.07795031
 * SEK -> VND = 2,710.5716
 * 
 * GBP -> EUR = 1.15834694
 * GBP -> USD = 1.32240509
 * GBP -> SEK = 12.83024994
 * GBP -> VND = 34,777.31
 * 
 * VND -> EUR = 0.00003330
 * VND -> USD = 0.00003802
 * VND -> SEK = 0.00036892
 * VND -> GBP = 0.00002875
 */
