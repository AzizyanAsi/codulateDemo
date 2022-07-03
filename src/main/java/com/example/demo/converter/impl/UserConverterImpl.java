package com.example.demo.converter.impl;

import com.example.demo.converter.PreferenceConverter;
import com.example.demo.converter.RoleConverter;
import com.example.demo.converter.UserConverter;
import com.example.demo.converter.dataTransferObjects.UserRequestDTO;
import com.example.demo.converter.dataTransferObjects.UserResponseDTO;
import com.example.demo.domain.User;
import com.example.demo.service.PreferenceService;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverterImpl implements UserConverter {

    private final RoleConverter roleConverter;
    private final RoleService roleService;
    private final PreferenceService prefService;
    private final PreferenceConverter prefConverter;



    @Override
    public User convertToEntity(UserRequestDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User entity = new User();
        entity.setFirstName(userDTO.getFirstName());
        entity.setLastName(userDTO.getLastName());
        entity.setUsername(userDTO.getUsername());
        entity.setPassword(userDTO.getPassword());
        entity.setEmail(userDTO.getEmail());
        entity.setPhoneNumber(userDTO.getPhoneNumber());
        entity.setDateOfBirth(userDTO.getDateOfBirth());
        entity.setHeight(userDTO.getHeight());
        entity.setWeight(userDTO.getWeight());
        for (Long x : userDTO.getRoleId()){
            entity.addRole(roleService.findById(x));

        }
        return entity;
    }

    @Override
    public UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setHeight(user.getHeight());
        dto.setWeight(user.getWeight());
        dto.setRoles(roleConverter.bulkConvertToDTO(user.getRoles()));
        return dto;
    }

    @Override
    public List<UserResponseDTO> bulkConvertToDTO(List<User> users) {
        return users.stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<User> bulkConvertToEntity(List<UserRequestDTO> userRequest) {
        return userRequest.stream().map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}

