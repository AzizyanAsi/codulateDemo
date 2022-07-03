package com.example.demo.converter.dataTransferObjects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UpdateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String dateOfBirth;
    private Double height;
    private Double weight;

}

