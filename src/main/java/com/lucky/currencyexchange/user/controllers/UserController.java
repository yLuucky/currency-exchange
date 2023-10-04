package com.lucky.currencyexchange.user.controllers;

import com.lucky.currencyexchange.user.controllers.views.UserView;
import com.lucky.currencyexchange.user.dtos.UserDTO;
import com.lucky.currencyexchange.user.infra.db.entities.User;
import com.lucky.currencyexchange.user.mappers.UserViewMapper;
import com.lucky.currencyexchange.user.services.ICreateUserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final ICreateUserService createUserService;

    @Autowired
    public UserController(final ICreateUserService createUserService) {
        this.createUserService = createUserService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created user"),
            @ApiResponse(responseCode = "400", description = "could not create a user")
    })
    @PostMapping
    public ResponseEntity<UserView> create(final UserDTO userDTO) {
        final User user = createUserService.execute(userDTO);
        return ResponseEntity.status(201).body(UserViewMapper.mapper(user));
    }
}
