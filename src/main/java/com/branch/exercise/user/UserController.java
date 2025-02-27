package com.branch.exercise.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {
    static private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/users/{username}")
    User getUser(@PathVariable String username) {
        LOGGER.trace("Handing getUser: {}", username);
        return service.getResponse(username);
    }
}