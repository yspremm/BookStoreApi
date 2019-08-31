package com.BookStoreApi.controller;

import com.BookStoreApi.constants.UsersConstants;
import com.BookStoreApi.exception.UsersException;
import com.BookStoreApi.model.StatusModel;
import com.BookStoreApi.model.Users;
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

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody Users body) throws UsersException {
        try {
            Users user = userService.createUser(body);
            return ResponseEntity.status(HttpStatus.OK).body(new StatusModel(UsersConstants.CREATE_SUCCESS.getMessage()));
        }catch (UsersException e) {
            UsersConstants usersConstants = e.getUsersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(usersConstants.getMessage()));
        }
    }

    @RequestMapping(path = "/login")
    public ResponseEntity<?> requestLogin(@Valid @RequestBody Users body) throws UsersException{
        try{
            Users user = userService.requestLogin(body);
            return ResponseEntity.status(HttpStatus.OK).body(new StatusModel(UsersConstants.LOGIN_SUCCESS.getMessage()));
        } catch (UsersException e){
            UsersConstants usersConstants = e.getUsersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(usersConstants.getMessage()));
        }

    }

    @GetMapping(path = "/users")
    public ResponseEntity<?>  getUserInfo() throws UsersException {
        try{
            GetUserInfo users = userService.getUserInfo();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (UsersException e) {
            UsersConstants usersConstants = e.getUsersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(usersConstants.getMessage()));
        }

    }

    @DeleteMapping(path = "/users")
    public ResponseEntity<?> deleteUser() throws UsersException {
        try {
            Users status = userService.deleteUser();
            return ResponseEntity.status(HttpStatus.OK).body(new StatusModel(UsersConstants.DELETE_SUCCESS.getMessage()));
        } catch (UsersException e){
            UsersConstants usersConstants = e.getUsersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(usersConstants.getMessage()));
        }

    }




}
