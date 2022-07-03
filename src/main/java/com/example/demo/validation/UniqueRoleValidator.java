package com.example.demo.validation;

import com.example.demo.service.RoleService;
import com.example.demo.validation.customAnnotations.UniqueRoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueRoleValidator implements ConstraintValidator<UniqueRoleName, String> {

    @Autowired
    private RoleService roleService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !roleService.isRoleNameAlreadyInUse(value);
    }

}
