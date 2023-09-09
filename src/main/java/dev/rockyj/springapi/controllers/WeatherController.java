package dev.rockyj.springapi.controllers;

import dev.rockyj.springapi.domain.Weather;
import dev.rockyj.springapi.services.UserService;
import dev.rockyj.springapi.services.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log
@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final UserService userService;
    private final WeatherService weatherService;

    @GetMapping("/user/{userId}")
    public Mono<Weather> getWeatherInformation(@PathVariable String cityName) {
        return weatherService.getWeather(cityName);
    }
}
