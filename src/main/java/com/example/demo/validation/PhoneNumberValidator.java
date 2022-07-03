package com.example.demo.validation;

import com.example.demo.validation.customAnnotations.PhoneNumberCustom;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator implements
        ConstraintValidator<PhoneNumberCustom, String> {

    private final String PHONE_PATTERN = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {

        return Pattern.matches(PHONE_PATTERN, phone);

    }
}