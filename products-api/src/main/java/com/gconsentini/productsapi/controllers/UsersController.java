package com.gconsentini.productsapi.controllers;

import com.gconsentini.productsapi.models.User;
import com.gconsentini.productsapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id){
        return usersService.getUserById(id);
    }

    @PostMapping(path = "/users/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User createUser(@RequestBody User user){
        return usersService.createUser(user);
    }


}
