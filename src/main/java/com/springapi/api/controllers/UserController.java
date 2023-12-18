package com.springapi.api.controllers;

import com.springapi.api.models.User;
import com.springapi.api.common.exceptions.UserNotFoundException;
import com.springapi.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        return userService.getUser(id)
                .orElseThrow(() -> new UserNotFoundException("User with id:" + id + " not found"));
    }
}
