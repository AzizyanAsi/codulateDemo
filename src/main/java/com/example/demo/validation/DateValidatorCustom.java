package com.example.demo.validation;

import com.example.demo.validation.customAnnotations.DateValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class DateValidatorCustom implements
        ConstraintValidator<DateValidator, String> {

    private final String DATE_PATTERN = "\\d{4}-\\d{2}-\\d{2}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches(DATE_PATTERN, value);
    }

}

