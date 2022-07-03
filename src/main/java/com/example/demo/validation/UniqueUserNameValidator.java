package com.example.demo.validation;

import com.example.demo.service.UserService;
import com.example.demo.validation.customAnnotations.UniqueUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.isUserNameAlreadyInUse(value);
    }
}
