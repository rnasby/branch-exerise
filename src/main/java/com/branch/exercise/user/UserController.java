package com.branch.exercise.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {
    static private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Get user")
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "200",
                description = "User found",
                content = {
                    @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = User.class)
                    )
                }
            ),
            @ApiResponse(
                responseCode = "400",
                description = "User not found or invalid input provided"
            )
    })
    @GetMapping(value = "/users/{username}")
    User getUser(@PathVariable String username) {
        LOGGER.trace("Handing getUser: {}", username);
        return service.getResponse(username);
    }
}