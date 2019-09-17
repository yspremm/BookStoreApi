package com.BookStoreApi.service;

import com.BookStoreApi.exception.OrdersException;
import com.BookStoreApi.exception.UsersException;
import com.BookStoreApi.model.Orders;
import com.BookStoreApi.model.Users;
import com.BookStoreApi.model.response.AccessTokenResponse;
import com.BookStoreApi.repositories.OrdersRepository;
import com.BookStoreApi.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    UsersRepository usersRepository;

    @Mock
    OrdersRepository ordersRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(usersRepository, ordersRepository);
    }

    public Optional<Users> optionalUsers() {
        Users user = new Users();
        Date date = new Date(15/01/1985);
        user.setId(1L);
        user.setAccessToken("461d24f4-5992-4817-a606-8d8eb87da5ac");
        user.setUsername("john");
        user.setPassword("$2a$10$RTSG8pWXJoVw4g031BwcMuujSSdAZC5qIp62iV/qC6Qc7lzavvjCO");
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setDate_of_birth(date);

        return Optional.of(user);
    }

    public Users usersReq() {
        Users user = new Users();
        Date date = new Date(15/01/1985);
        user.setAccessToken("461d24f4-5992-4817-a606-8d8eb87da5ac");
        user.setUsername("john");
        user.setPassword("thisismysecret");
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setDate_of_birth(date);
        return user;
    }

    public Users usersRes() {
        Users user = new Users();
        Date date = new Date(15/01/1985);
        user.setId(1L);
        user.setAccessToken("461d24f4-5992-4817-a606-8d8eb87da5ac");
        user.setUsername("john");
        user.setPassword("thisismysecret");
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setDate_of_birth(date);
        return user;
    }

    public AccessTokenResponse accessToken(){
        AccessTokenResponse res = new AccessTokenResponse();
        res.setAccess_token("461d24f4-5992-4817-a606-8d8eb87da5ac");
        res.setToken_type("bearer");
        res.setExpires_in(475);
        res.setScope("read write trust");
        return res;
    }

    @DisplayName("Test create user success ")
    @Test
    public void testCreateUserSuccess() throws Exception{
        Mockito.when(usersRepository.findByUsername("john")).thenReturn(Optional.empty());

        assertDoesNotThrow(()->
                userService.createUser(this.usersReq())
        );
    }

    @DisplayName("Test create user fail ")
    @Test
    public void testCreateUserFail() throws Exception{
        Optional<Users> users = this.optionalUsers();
        Mockito.when(usersRepository.findByUsername("john")).thenReturn(users);

        UsersException thrown = assertThrows(UsersException.class,
                () -> userService.createUser(this.usersReq()));

        assertEquals("cannot create user account, username already exists", thrown.getUsersConstants().getMessage());

    }

    @DisplayName("Test request login user success ")
    @Test
    public void testRequestLoginUserSuccess() throws Exception{
        Optional<Users> users = this.optionalUsers();
        Mockito.when(usersRepository.findByUsername("john")).thenReturn(users);

        assertDoesNotThrow(()->
                userService.requestLogin(this.usersRes(), this.accessToken())
        );
    }

    @DisplayName("Test request login user fail ")
    @Test
    public void testRequestLoginUserFail() throws Exception{
        Optional<Users> users = this.optionalUsers();

        Mockito.when(usersRepository.findByUsername("john")).thenReturn(Optional.empty());

        BCryptPasswordEncoder passwordFromBody = new BCryptPasswordEncoder();
        boolean booleanhashed = passwordFromBody.matches(body.getPassword(), users.get().getPassword());

        UsersException thrown = assertThrows(UsersException.class,
                () -> userService.requestLogin(this.usersRes(),this.accessToken());

        assertEquals("username or password incorrect", thrown.getUsersConstants().getMessage());
    }


}
