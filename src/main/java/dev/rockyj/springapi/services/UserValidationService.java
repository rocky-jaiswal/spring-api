package dev.rockyj.springapi.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.regex.Pattern;

@Component
@Log
public class UserValidationService {

    private static final Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    public Mono<UUID> isValidUserId(String userId) {
        if (UUID_REGEX.matcher(userId).matches()) {
            return Mono.just(UUID.fromString(userId));
        }
        throw new RuntimeException("Bad user id!");
    }
}
