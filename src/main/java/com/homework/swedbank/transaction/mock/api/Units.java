package com.homework.swedbank.transaction.mock.api;

public record Units(
        String time,
        String temperature_2m,
        String relative_humidity_2m,
        String precipitation_probability,
        String precipitation,
        String weather_code) {

}
