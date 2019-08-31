package com.BookStoreApi.controller;

import com.BookStoreApi.api.BookStoreApi;
import com.BookStoreApi.model.response.GetBooksInfoResponse;
import com.BookStoreApi.model.response.GetListBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/bookstore")
public class BooksController {

    private BookStoreApi bookStoreApi;

    @Autowired
    public BooksController(BookStoreApi bookStoreApi) {
        this.bookStoreApi = bookStoreApi;
    }

    @GetMapping(path = "/books")
    public ResponseEntity<?> getBookInfo() throws Exception{
        List<GetBooksInfoResponse> bookInfo = bookStoreApi.getBookInfo();
        GetListBook listBook = new GetListBook();
        listBook.setBooks(bookInfo);
        return ResponseEntity.ok(listBook);
    }

}
