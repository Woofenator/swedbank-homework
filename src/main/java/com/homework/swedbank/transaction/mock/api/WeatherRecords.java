package com.homework.swedbank.transaction.mock.api;

import java.util.List;

public record WeatherRecords(
        List<String> time,
        List<Double> temperature_2m,
        List<Integer> relative_humidity_2m,
        List<Integer> precipitation_probability,
        List<Double> precipitation,
        List<Integer> weather_code) {

}
