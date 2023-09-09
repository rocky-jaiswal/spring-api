package dev.rockyj.springapi.repositories;

import dev.rockyj.springapi.entities.UserCity;
import dev.rockyj.springapi.entities.UserPreference;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserCitiesRepository extends R2dbcRepository<UserCity, UUID> {

    @Query("select id, user_id, city_name from user_cities where user_id = :userId")
    Flux<UserCity> findByUserId(UUID userId);
}
