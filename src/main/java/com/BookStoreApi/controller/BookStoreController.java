package com.BookStoreApi.controller;

import com.BookStoreApi.model.User;
import com.BookStoreApi.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

}
