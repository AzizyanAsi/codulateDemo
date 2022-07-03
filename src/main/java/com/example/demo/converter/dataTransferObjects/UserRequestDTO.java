package com.example.demo.converter.dataTransferObjects;

import com.example.demo.validation.customAnnotations.DateValidator;
import com.example.demo.validation.customAnnotations.PhoneNumberCustom;
import com.example.demo.validation.customAnnotations.UniqueEmail;
import com.example.demo.validation.customAnnotations.UniqueUserName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UserRequestDTO {

    @NotBlank(message = "firstName must not be blank")
    private String firstName;

    @NotBlank(message = "lastName must not be blank")
    private String lastName;

    @UniqueEmail
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email must have correct syntax")
    private String email;

    @PhoneNumberCustom
    private String phoneNumber;

    @Size(min = 3, message = "username is required")
    @UniqueUserName
    private String username;

    @Size(min = 6, message = "password is required")
    private String password;

    @DateValidator
    @NotNull(message = "The date of birth is required")
    private String dateOfBirth;

    @Positive(message = "phone must be positive")
    @Digits(integer = 1, fraction = 2)
    private Double height;

    @Positive(message = "phone must be positive")
    @Digits(integer = 3, fraction = 0)
    private Double weight;

    private List<Long> roleId = new ArrayList<>();

}
