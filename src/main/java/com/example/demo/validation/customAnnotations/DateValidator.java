package com.example.demo.validation.customAnnotations;

import com.example.demo.validation.DateValidatorCustom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidatorCustom.class)
public @interface DateValidator {

    String message() default "The date must have correct style like YYYY/DD/MM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
