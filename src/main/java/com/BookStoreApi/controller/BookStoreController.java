package com.BookStoreApi.controller;

import com.BookStoreApi.model.User;
import com.BookStoreApi.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookstore")
public class BookStoreController {

    private BookStoreService bookStoreService;

    @Autowired
    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @RequestMapping(path = "/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User body){
        User user = bookStoreService.createUser(body);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @RequestMapping(path = "/login")
    public ResponseEntity<?> createLogin(@Valid @RequestBody User body){
        boolean user = bookStoreService.createLogin(body);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping(path = "/users")
    public ResponseEntity<?> deleteUser(){
        boolean status = bookStoreService.deleteUser();
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }


}
