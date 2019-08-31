package com.BookStoreApi.controller;

import com.BookStoreApi.api.BookStoreApi;
import com.BookStoreApi.model.response.GetBooksInfo;
import com.BookStoreApi.model.response.GetListBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger log = LogManager.getLogger(BooksController.class.getName());

    @Autowired
    public BooksController(BookStoreApi bookStoreApi) {
        this.bookStoreApi = bookStoreApi;
    }

    @GetMapping(path = "/books")
    public ResponseEntity<?> getBookInfo() throws Exception{
        List<GetBooksInfo> bookInfo = bookStoreApi.getBookInfo();
        List<GetBooksInfo> bookRecommend = bookStoreApi.getBookRecommend();
        for (int i=0;i<bookRecommend.size();i++){
            bookRecommend.get(i).setIsRecommended(true);
            log.info(bookRecommend.get(i));

        }
        GetListBook listBook = new GetListBook();
        listBook.setBooks(bookRecommend);
        return ResponseEntity.ok(listBook);
    }

}
