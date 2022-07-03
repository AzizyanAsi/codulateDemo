package com.example.demo.validation.customAnnotations;

import com.example.demo.validation.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)

public @interface PhoneNumberCustom {
    String message() default "phone number must be contain country code,correct example +91 (987)6543210";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
