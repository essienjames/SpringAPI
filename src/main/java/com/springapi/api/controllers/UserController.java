package com.springapi.api.controllers;

import com.springapi.api.models.Users;
import com.springapi.api.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public Iterable<Users> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") int id) {
        Users user = userService.findById(id);
        LOGGER.info("User found with id: {}", id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable(name = "email") String email) {
        Users user = userService.findByEmail(email);
        LOGGER.info("User found with email: {}", email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Users> addUser(@RequestBody Users user) {
        LOGGER.info("User created: {}", user);
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(name = "id") int id, @RequestBody Users user) {
        try {
            Users updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable(name = "id") int id) {
        Users deletedUser = userService.deleteUser(id);
        LOGGER.info("User deleted with id: {}", id);
        return ResponseEntity.ok(deletedUser);
    }
}
