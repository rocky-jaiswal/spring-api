package dev.rockyj.springapi.controllers;

import dev.rockyj.springapi.entities.User;
import dev.rockyj.springapi.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Log
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping("/")
    public Flux<User> list() {
        log.info("In get all users ..." + Thread.currentThread().getName());
        return userService.listAll();
    }

}
