package com.BookStoreApi.exception;

import com.BookStoreApi.constants.BooksConstants;
import com.BookStoreApi.constants.OrdersConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class BooksException extends Exception {

    private BooksConstants booksConstants;
    private HttpStatus httpStatus;

    public BooksException(BooksConstants booksConstants, HttpStatus httpStatus){
        this.booksConstants = booksConstants;
        this.httpStatus = httpStatus;
    }

}
