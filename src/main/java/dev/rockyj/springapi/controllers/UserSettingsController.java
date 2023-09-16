package dev.rockyj.springapi.controllers;

import dev.rockyj.springapi.domain.UserSettings;
import dev.rockyj.springapi.domain.Weather;
import dev.rockyj.springapi.entities.User;
import dev.rockyj.springapi.entities.UserCity;
import dev.rockyj.springapi.entities.UserPreference;
import dev.rockyj.springapi.services.UserService;
import dev.rockyj.springapi.services.UserValidationService;
import dev.rockyj.springapi.services.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;


import java.util.List;
import java.util.Map;

@Log
@RestController
@RequestMapping("/v1/user-settings")
@RequiredArgsConstructor
public class UserSettingsController {

    private final UserService userService;
    private final UserValidationService userValidationService;
    private final WeatherService weatherService;

    @GetMapping("/{userId}")
    public Mono<UserSettings> getUserPreferences(@PathVariable String userId) {
        return userValidationService
                .isValidUserId(userId)
                .flatMap((var userIdUUID) -> {
                    var monoOfUser = userService.findByUserId(userIdUUID);
                    var monoOfUserPreferences = userService.findPreferencesByUserId(userIdUUID);
                    var fluxOfUserCities = userService.findCitiesByUserId(userIdUUID);

                    return Mono.zip(monoOfUser, monoOfUserPreferences, fluxOfUserCities.collectList());
                })
                .flatMap((Tuple3<User, UserPreference, List<UserCity>> tuple) -> {
                    var cities = tuple.getT3();
                    var fluxOfWeather = Flux.fromIterable(cities).flatMap((var city) -> weatherService.getWeather(city.getCityName()));

                    return Mono.zip(Mono.just(tuple.getT1()), Mono.just(tuple.getT2()), fluxOfWeather.collectList());
                })
                .flatMap((Tuple3<User, UserPreference, List<Weather>> tuple) -> {
                    var userSettings = new UserSettings(
                            tuple.getT1().getEmail(),
                            tuple.getT2().isMember(),
                            tuple.getT3());

                    return Mono.just(userSettings);
                });
    }

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleIllegalState(ServerWebExchange exchange, RuntimeException exc) {
        exchange.getAttributes().putIfAbsent(ErrorAttributes.ERROR_ATTRIBUTE, exc);
        exchange.getResponse().setStatusCode(HttpStatusCode.valueOf(500));
        return Map.of("error", exc.getMessage());
    }

}
