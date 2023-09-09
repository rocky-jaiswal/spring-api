package dev.rockyj.springapi.services;

import dev.rockyj.springapi.entities.User;
import dev.rockyj.springapi.entities.UserCity;
import dev.rockyj.springapi.entities.UserPreference;
import dev.rockyj.springapi.repositories.UserCitiesRepository;
import dev.rockyj.springapi.repositories.UserPreferencesRepository;
import dev.rockyj.springapi.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Log
public class UserService {
    private final UsersRepository usersRepository;
    private final UserPreferencesRepository userPreferencesRepository;
    private final UserCitiesRepository userCitiesRepository;

    public Flux<User> listAll() {
        return usersRepository.findAll();
    }

    public Mono<User> findByUserId(UUID userId) {
        log.info("->1");
        return usersRepository.findById(userId);
    }

    public Mono<UserPreference> findPreferencesByUserId(UUID userId) {
        log.info("->2");
        return userPreferencesRepository.findByUserId(userId);
    }

    public Flux<UserCity> findCitiesByUserId(UUID userId) {
        log.info("->3");
        return Mono.delay(Duration.ofSeconds(3)).thenMany(userCitiesRepository.findByUserId(userId));
    }
}
