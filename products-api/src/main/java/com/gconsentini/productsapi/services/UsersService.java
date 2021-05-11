package com.gconsentini.productsapi.services;

import com.gconsentini.productsapi.UserOuterClass;
import com.gconsentini.productsapi.UserServiceGrpc;
import com.gconsentini.productsapi.models.User;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class UsersService {


    private final UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub = UserServiceGrpc.newBlockingStub(ManagedChannelBuilder.forAddress("localhost", 3334).usePlaintext().build());

    public User createUser(User userParams){
        UserOuterClass.UserResponse response = userServiceBlockingStub.registerUser(
                UserOuterClass.RegisterUserRequest.newBuilder().setUser(
                        UserOuterClass.User.newBuilder()
                                .setFirstName(userParams.getFirstName())
                                .setLastName(userParams.getLastName())
                                .setDateOfBirth("12-21-1990")
                                .build()
                ).build()
        );
        User userResponse = new User();
        userResponse.setId("" + response.getUser().getId());
        userResponse.setFirstName(response.getUser().getFirstName());
        userResponse.setLastName(response.getUser().getLastName());
        try {
            userResponse.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(response.getUser().getDateOfBirth()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return userResponse;
    }

    public User getUserById(String id){
        return null;
    }

}