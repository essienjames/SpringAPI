package com.springapi.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * Represents a user entity.
 */
@Getter
@Entity
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indicates auto-increment strategy
    @Setter(AccessLevel.NONE)
    private Long id;

    private String firstname;
    private String lastname;
    private String mobile;
    private String email;
    private int age;

    @Setter(AccessLevel.NONE)
    private String currency;

    @Setter(AccessLevel.NONE)
    private int balance;
}
