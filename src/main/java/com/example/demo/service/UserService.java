package com.example.demo.service;

import com.example.demo.converter.dataTransferObjects.UpdateRequest;
import com.example.demo.converter.dataTransferObjects.UserRequestDTO;
import com.example.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(UserRequestDTO userRequest);

    User update(String username, UpdateRequest userRequest);

    User findByUsername(String username);

    Boolean isUserNameAlreadyInUse(String name);

    Boolean isEmailAlreadyInUse(String name);

    User findById(Long id);

    List<User> getAllUsers();

    List<User> getAllUsersByWeight(Double weight);

    boolean deleteById(Long id);

}
