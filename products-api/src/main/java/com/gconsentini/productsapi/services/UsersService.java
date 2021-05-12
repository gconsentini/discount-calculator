package com.gconsentini.productsapi.services;

import com.gconsentini.productsapi.UserOuterClass;
import com.gconsentini.productsapi.UserServiceGrpc;
import com.gconsentini.productsapi.models.User;
import lombok.SneakyThrows;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UsersService {

    @GrpcClient("user-server")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @SneakyThrows
    public User createUser(User userParams){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());;
        String isoStringDate = formatter.format(userParams.getDateOfBirth().toInstant());
        UserOuterClass.UserResponse response = userServiceBlockingStub.registerUser(
                UserOuterClass.RegisterUserRequest.newBuilder().setUser(
                        UserOuterClass.User.newBuilder()
                                .setFirstName(userParams.getFirstName())
                                .setLastName(userParams.getLastName())
                                .setDateOfBirth(isoStringDate)
                                .build()
                ).build()
        );
        return getUserByUserResponse(response);
    }

    public User getUserById(String id){
        UserOuterClass.UserResponse response = userServiceBlockingStub.getUserById(
                UserOuterClass.GetUserByIdRequest.newBuilder().setId(id).build()
        );

        return getUserByUserResponse(response);
    }

    private User getUserByUserResponse(UserOuterClass.UserResponse userResponse){
        return User.builder()
                .id(userResponse.getUser().getId())
                .firstName(userResponse.getUser().getFirstName())
                .lastName(userResponse.getUser().getLastName())
                .dateOfBirth(Date.from(Instant.from(Instant.parse(userResponse.getUser().getDateOfBirth()).atZone(ZoneId.systemDefault()))))
                .build();
    }
}