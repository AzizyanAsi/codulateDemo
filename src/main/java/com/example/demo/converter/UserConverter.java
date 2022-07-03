package com.example.demo.converter;

import com.example.demo.converter.dataTransferObjects.UserRequestDTO;
import com.example.demo.converter.dataTransferObjects.UserResponseDTO;
import com.example.demo.domain.User;

import java.util.List;

public interface UserConverter {

    User convertToEntity(UserRequestDTO userDTO);

    UserResponseDTO convertToDTO(User user);

    List<UserResponseDTO> bulkConvertToDTO(List<User> users);

    List<User> bulkConvertToEntity(List<UserRequestDTO> users);
}
