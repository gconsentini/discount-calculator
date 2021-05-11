package com.gconsentini.productsapi.controllers;

import com.gconsentini.productsapi.models.User;
import com.gconsentini.productsapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable String id){

        return new User();
    }

    @GetMapping("/users/")
    public List<User> getAllUsers(){
        return Collections.EMPTY_LIST;
    }

    @PostMapping(path = "/users/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User createUser(@RequestBody User user){
        return usersService.createUser(user);
    }


}
