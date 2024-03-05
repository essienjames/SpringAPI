package com.springapi.api.controllers;

import com.springapi.api.models.Users;
import com.springapi.api.common.exceptions.UserNotFoundException;
import com.springapi.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    public Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") int id) {
        LOGGER.info("Fetching user with id: {}", id);
        if (id == 0) {
            LOGGER.warn("Invalid id provided");
            throw new UserNotFoundException("Invalid id provided");
        } else {
            try {
                Users user = userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
                LOGGER.info("User found with id: {}", id);
                return ResponseEntity.ok(user);
            } catch (UserNotFoundException exception) {
                LOGGER.warn("User not found with id: {}", id);
                throw new UserNotFoundException("User with id:" + id + " not found");
            }
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable(name = "email") String email) {
        LOGGER.info("Fetching user with email: {}", email);
        if (email == null) {
            LOGGER.warn("Invalid email provided");
            throw new UserNotFoundException("Invalid email provided");
        } else {
            try {
                Users user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("User with email:" + email + " not found"));
                LOGGER.info("User found with email: {}", email);
                return ResponseEntity.ok(user);
            } catch (UserNotFoundException exception) {
                LOGGER.warn("User not found with email: {}", email);
                throw new UserNotFoundException("User with email:" + email + " not found");
            }
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Users addUser(@RequestBody Users user) {
        LOGGER.info("Adding new user: {}", user);
        return userRepository.save(user);
    }

    // todo add update user and delete user methods
}
