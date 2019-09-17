package com.BookStoreApi.controller;

import com.BookStoreApi.api.AccessTokenApi;
import com.BookStoreApi.constants.UsersConstants;
import com.BookStoreApi.exception.UsersException;
import com.BookStoreApi.model.StatusModel;
import com.BookStoreApi.model.Users;
import com.BookStoreApi.model.response.AccessTokenResponse;
import com.BookStoreApi.model.response.GetUserInfo;
import com.BookStoreApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/bookstore")
public class UsersController {

    private UserService userService;
    private AccessTokenApi accessTokenApi;

    @Autowired
    public UsersController(UserService userService,AccessTokenApi accessTokenApi) {
        this.userService = userService;
        this.accessTokenApi = accessTokenApi;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody Users body) throws UsersException {
        try {
            userService.createUser(body);
            return ResponseEntity.status(HttpStatus.OK).body(new StatusModel(UsersConstants.CREATE_SUCCESS.getMessage()));
        }catch (UsersException e) {
            UsersConstants usersConstants = e.getUsersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(usersConstants.getMessage()));
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> requestLogin(@Valid @RequestBody Users body) throws Exception {
        try{
            AccessTokenResponse token = accessTokenApi.getAccessToken(body.getUsername(), body.getPassword());
            userService.requestLogin(body, token);
            return ResponseEntity.status(HttpStatus.OK).body(new StatusModel(UsersConstants.LOGIN_SUCCESS.getMessage()));
        } catch (UsersException e){
            UsersConstants usersConstants = e.getUsersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(usersConstants.getMessage()));
        }

    }

    @GetMapping(path = "/users")
    public ResponseEntity<?>  getUserInfo(@RequestHeader String access_token) throws UsersException {
        try{
            GetUserInfo users = userService.getUserInfo(access_token);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (UsersException e) {
            UsersConstants usersConstants = e.getUsersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(usersConstants.getMessage()));
        }

    }

    @DeleteMapping(path = "/users")
    public ResponseEntity<?> deleteUser(@RequestHeader String access_token) throws UsersException {
        try {
            userService.deleteUser(access_token);
            return ResponseEntity.status(HttpStatus.OK).body(new StatusModel(UsersConstants.DELETE_SUCCESS.getMessage()));
        } catch (UsersException e){
            UsersConstants usersConstants = e.getUsersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(usersConstants.getMessage()));
        }

    }




}
