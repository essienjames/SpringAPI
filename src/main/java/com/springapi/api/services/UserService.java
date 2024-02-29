package com.springapi.api.services;

import com.springapi.api.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> userList = new ArrayList<>();

    //todo remove hardcoded data replace with database implementation
    public UserService() {
        userList.add(new User(1,"Gordon","Freeman", 45,"07364736252","USD",70000,"freeman@blackmesa.com"));
        userList.add(new User(2,"Harry","Potter",36,"07354629182","GBP",55000, "imawizard@hogwarts.com"));
        userList.add(new User(3,"Roboute","Guilliman",100,"07884957635","GBP",100000,"guilliboy@imperium.com"));
        userList.add(new User(4,"Master","Chief",32,"07123890947","USD",10000, "john117@unsc.com"));
        userList.add(new User(5,"Geralt","Rivia",65,"07354625341","PLN",25000, "butcher@blavakin.com"));
    }

    public Optional<User> getUser(Integer id) {
        return userList.stream()
                .filter(it -> id.equals(it.getId()))
                .findFirst();
    }

    public User addUser(Integer id, String firstName, String lastName, Integer age, String mobile, String currency, Integer salary, String email) {
        User user = new User(id, firstName, lastName, age, mobile, currency, salary, email);
        userList.add(user);
        return user;
    }
    /**
     * todo addUser method
     * add age verification check (18+)
     * Check all fields are filled
     * firstName and lastName Syntax check: can't be single characters or contain numbers or symbols
     */

}
