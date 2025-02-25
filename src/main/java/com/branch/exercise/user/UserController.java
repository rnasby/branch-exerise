package com.branch.exercise.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {
    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/users/{username}")
    User getUser(@PathVariable String username) {
        return service.getResponse(username);
    }
}