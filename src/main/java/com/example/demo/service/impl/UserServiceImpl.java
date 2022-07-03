package com.example.demo.service.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.converter.dataTransferObjects.UpdateRequest;
import com.example.demo.converter.dataTransferObjects.UserRequestDTO;
import com.example.demo.domain.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User register(UserRequestDTO userDTO) {
        LOGGER.debug("Requested to create user for email : {}", userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(userConverter.convertToEntity(userDTO));
    }

    @Override
    @Transactional
    public User update(String username, UpdateRequest userRequest) {
        LOGGER.info("Requested to register a user with username {}", username);
        final User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No user found by this username {}", username));
        //TODO mi field set  aneluc  null a dnum mnacacy /have any other way to do this?

        if (user.isProfileUpdated()) {
            if (userRequest.getFirstName() != null) {
                user.setFirstName(userRequest.getFirstName());
            } else if (userRequest.getLastName() != null) {
                user.setLastName(userRequest.getLastName());
            } else if (userRequest.getEmail() != null) {
                user.setEmail(userRequest.getEmail());
            } else if (userRequest.getPhoneNumber() != null) {
                user.setPhoneNumber(userRequest.getPhoneNumber());
            } else if (userRequest.getDateOfBirth() != null) {
                user.setDateOfBirth(userRequest.getDateOfBirth());
            } else if (userRequest.getHeight() != null) {
                user.setHeight(userRequest.getHeight());
            } else if (userRequest.getWeight() != null) {
                user.setWeight(userRequest.getWeight());
            } else if (userRequest.getUsername() != null) {
                user.setUsername(userRequest.getUsername());
            } else if (userRequest.getPassword() != null) {
                user.setPassword(userRequest.getPassword());
            }
        }
        LOGGER.info("In update User, user with username {} successfully updated", username);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        LOGGER.info("In findByUsername requested to get the user with username {}", username);
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No user found by this username", username));
    }

    @Override
    @Transactional
    public Boolean isUserNameAlreadyInUse(String name) {
        return userRepository.findByUsername(name).isPresent();
    }

    @Override
    public Boolean isEmailAlreadyInUse(String name) {
        return userRepository.findByEmail(name).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        LOGGER.debug("In findById requested to get the user with  id: {}", id);
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Not found user by this id: {}", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        final List<User> users = this.userRepository.findAllByDateOfBirthAfter();//TODO false
        LOGGER.debug("In find all : {} user found", users.size());
        return users;
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsersByWeight(Double weight) {
        return this.userRepository.findAllByWeightGreaterThan(weight);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        LOGGER.info("Requested to delete a user with id {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No user found by this id", id);
        }
        userRepository.deleteById(id);
        LOGGER.info(" User with id {} successfully deleted", id);
        return true;
    }

}
