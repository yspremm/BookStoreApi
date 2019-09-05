package com.BookStoreApi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Please type your username size between 1 - 100")
    private String username;

    @NotNull
    private String password;

    private String accessToken;

    private String firstname;

    private String lastname;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date_of_birth;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;

    private boolean active;

    public Users(String username, String password, List<Role> roles, boolean active)
    {
        this.username=username;
        this.password=password;
        this.roles=roles;
        this.active=active;

    }
}
