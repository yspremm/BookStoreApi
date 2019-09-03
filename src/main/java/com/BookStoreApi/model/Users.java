package com.BookStoreApi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your username size between 1 - 100")
    private String username;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your password size between 1 - 100")
    private String password;

    @Size(min = 1, max = 100, message = "Please type your firstname size between 1 - 100")
    private String firstname;

    @Size(min = 1, max = 100, message = "Please type your lastname size between 1 - 100")
    private String lastname;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date_of_birth;

//    @Column(length = 8)
    private Enum status;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;

    private boolean active;

}
