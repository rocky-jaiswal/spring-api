package dev.rockyj.springapi.repositories;

import dev.rockyj.springapi.entities.UserPreference;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserPreferencesRepository extends R2dbcRepository<UserPreference, UUID> {

    @Query("select id, user_id, is_member, pg_sleep(3) from user_preferences where user_id = :userId")
    Mono<UserPreference> findByUserId(UUID userId);
}
