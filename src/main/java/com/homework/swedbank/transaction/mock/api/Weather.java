package com.homework.swedbank.transaction.mock.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Weather(
        double latitude,
        double longitude,
        double generationtime_ms,
        int utc_offset_seconds,
        String timezone,
        String timezone_abbreviation,
        double elevation,
        Units hourly_units,
        WeatherRecords hourly) {

}
