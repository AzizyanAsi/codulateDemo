package com.example.demo.converter.dataTransferObjects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UserResponseDTO {

    private Long   id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String dateOfBirth;
    private Double height;
    private Double weight;
    private List<RoleResponseDTO> roles= new ArrayList<>();
}
