package com.BookStoreApi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your username size between 1 - 100")
    private String username;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your password size between 1 - 100")
    private String password;

    @NotNull
    @Size(min = 1, max = 10, message = "Please type your date_of_birth size between 1 - 10")
    private String date_of_birth;
}
