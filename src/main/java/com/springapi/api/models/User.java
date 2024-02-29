package com.springapi.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String mobile;
    private String currency;
    private int balance;
    private String email;

    public User(int id, String firstName, String lastName, int age, String mobile, String currency, int balance, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.mobile = mobile;
        this.currency = currency;
        this.balance = balance;
        this.email = email;
    }

    public User() {

    }
}
