package com.example.demo.validation;

import com.example.demo.service.UserService;
import com.example.demo.validation.customAnnotations.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return value != null && !userService.isEmailAlreadyInUse(value);
    }
}
