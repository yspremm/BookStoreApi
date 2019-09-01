package com.BookStoreApi.controller;

import com.BookStoreApi.api.BookStoreApi;
import com.BookStoreApi.constants.BooksConstants;
import com.BookStoreApi.exception.BooksException;
import com.BookStoreApi.model.StatusModel;
import com.BookStoreApi.model.response.GetBooksInfo;
import com.BookStoreApi.model.response.GetListBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
        try{
            List<GetBooksInfo> allBook = bookStoreApi.getBookInfo();
            List<GetBooksInfo> recommendBook = bookStoreApi.getBookRecommend();
            List<GetBooksInfo> trueRecommend = bookStoreApi.getBookRecommend();
            recommendBook.addAll(allBook);
            List<GetBooksInfo> listWithoutDuplicates = recommendBook.stream().distinct().collect(Collectors.toList());
            for (int i=0;i<trueRecommend.size();i++) {
                if (listWithoutDuplicates.get(i).getId().equals(trueRecommend.get(i).getId())){
                    listWithoutDuplicates.get(i).setIsRecommended(true);
                }
            }

            GetListBook listBook = new GetListBook();
            listBook.setBooks(listWithoutDuplicates);
            return ResponseEntity.ok(listWithoutDuplicates);
        } catch (BooksException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(BooksConstants.CANNOT_GET_BOOK.getMessage()));
        }

    }

}
