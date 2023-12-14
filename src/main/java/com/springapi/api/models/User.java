package com.springapi.api.models;

import lombok.Data;

@Data
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String contactNumber;
    private String currencyType;
    private int balance;
    private String email;

    public User(int id, String firstName, String lastName, int age, String contactNumber, String currencyType, int balance, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.contactNumber = contactNumber;
        this.currencyType = currencyType;
        this.balance = balance;
        this.email = email;
    }
}
