package com.BookStoreApi.constants;

import lombok.Getter;

@Getter
public enum BooksConstants {
    CANNOT_GET_BOOK("cannot get list of books");

    private String message;
    BooksConstants(String message) {
        this.message = message;
    }
}
