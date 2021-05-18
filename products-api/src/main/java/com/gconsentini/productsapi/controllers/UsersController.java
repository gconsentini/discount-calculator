package com.gconsentini.productsapi.controllers;

import com.gconsentini.productsapi.models.User;
import com.gconsentini.productsapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return new ResponseEntity<>(usersService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/users/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.OK);
    }


}
