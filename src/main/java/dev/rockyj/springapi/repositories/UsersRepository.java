package dev.rockyj.springapi.repositories;

import dev.rockyj.springapi.entities.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface UsersRepository extends R2dbcRepository<User, UUID> {

    @Override
    @Query("select id, name, email, pg_sleep(2) from users")
    Flux<User> findAll();
}
