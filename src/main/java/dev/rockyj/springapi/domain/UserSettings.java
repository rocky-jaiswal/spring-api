package dev.rockyj.springapi.domain;

import java.util.List;

public record UserSettings(String email, Boolean isMember, List<Weather> citiesWeather) {
}
