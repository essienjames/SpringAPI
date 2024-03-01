package com.springapi.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
//    private int id;
    private String first_name;
    private String last_name;
    private int age;
    private String mobile;
    private String currency;
    private int balance;
    private String email;
}
