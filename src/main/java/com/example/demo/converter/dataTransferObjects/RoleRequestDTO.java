package com.example.demo.converter.dataTransferObjects;

import com.example.demo.validation.customAnnotations.UniqueRoleName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class RoleRequestDTO {

    @UniqueRoleName
    private String name;

    @NotBlank
    private String description;

}
