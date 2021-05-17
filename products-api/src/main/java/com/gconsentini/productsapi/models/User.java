package com.gconsentini.productsapi.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder
@Data
public class User {

    private String id;

    @JsonProperty("first_name")
    @NotBlank(message = "Nome não pode ser vazio")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Sobrenome não pode ser vazio")
    private String lastName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    public User(String id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

}
