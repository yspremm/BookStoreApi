package com.BookStoreApi.controller;

import com.BookStoreApi.model.Users;
import com.BookStoreApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/bookstore")
public class UsersController {

    private UserService bookStoreService;

    @Autowired
    public UsersController(UserService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @RequestMapping(path = "/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody Users body){
        Users user = bookStoreService.createUser(body);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @RequestMapping(path = "/login")
    public ResponseEntity<?> createLogin(@Valid @RequestBody Users body){
        boolean user = bookStoreService.createLogin(body);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping(path = "/users")
    public ResponseEntity<?> deleteUser(){
        boolean status = bookStoreService.deleteUser();
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }




}
