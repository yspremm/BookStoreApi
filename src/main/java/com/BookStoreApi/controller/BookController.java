package com.BookStoreApi.controller;

import com.BookStoreApi.api.BookStoreApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bookstore")
public class BookController {

    private BookStoreApi bookStoreApi;

    @Autowired
    public BookController(BookStoreApi bookStoreApi) {
        this.bookStoreApi = bookStoreApi;
    }

    @GetMapping(path = "/books")
    public ResponseEntity<?> getBookInfo() throws Exception{
        return ResponseEntity.ok(bookStoreApi.getBookInfo());
    }
}
