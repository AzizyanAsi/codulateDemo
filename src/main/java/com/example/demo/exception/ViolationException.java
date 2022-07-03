package com.example.demo.exception;


import com.example.demo.validation.ValidationError;
import lombok.Getter;

public class ViolationException extends RuntimeException {

    @Getter
    private final ValidationError validationError;

    public ViolationException(ValidationError validationError) {
        this.validationError = validationError;
    }

}
