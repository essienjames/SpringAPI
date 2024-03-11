package com.springapi.api.services;

import com.springapi.api.common.exceptions.UserNotFoundException;
import com.springapi.api.controllers.UserController;
import com.springapi.api.models.Users;
import com.springapi.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users findById(int id) {
        LOGGER.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
    }

    public Users findByEmail(String email) {
        LOGGER.info("Fetching user with email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email:" + email + " not found"));
    }

    public Users createUser(Users user) {
        LOGGER.info("Adding new user: {}", user);
        return userRepository.save(user);
    }

    public Users updateUser(int id, Users user) {
        LOGGER.info("Updating user with id: {}", id);
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));

        // Perform validation for age
        if (user.getAge() < 18) {
            throw new IllegalArgumentException("You must be 18 or over to use this service.");
        }

        // Update user details
        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setAge(user.getAge());
        existingUser.setMobile(user.getMobile());
        existingUser.setEmail(user.getEmail());

        return userRepository.save(existingUser);
    }

    public Users deleteUser(int id) {
        LOGGER.info("Deleting user with id: {}", id);
        Users userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));

        userRepository.delete(userToDelete);
        return userToDelete;
    }
}
