package com.example.springapi.service;

import com.example.springapi.api.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> userList;

    public UserService() {
        userList = new ArrayList<>();
        User user1 = new User(1, "John", "john@mail.com", 25);
        User user2 = new User(2, "Jane", "jane@mail.com", 30);
        User user3 = new User(3, "Max", "max@mail.com", 35);
        User user4 = new User(4, "Alex", "alex@mail.com", 40);
        User user5 = new User(5, "Mark", "mark@mail.com", 45);

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }

    public Optional<User> getUser(Integer id) {
        Optional optional = Optional.empty();
        for (User user : userList) {
            if (user.getId() == id) {
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }
}
