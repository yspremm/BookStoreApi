package com.BookStoreApi.exception;

import com.BookStoreApi.constants.UsersConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class UsersException extends Exception {

    private UsersConstants usersConstants;
    private HttpStatus httpStatus;

    public UsersException(UsersConstants usersConstants, HttpStatus httpStatus) {
        this.usersConstants = usersConstants;
        this.httpStatus = httpStatus;
    }

}
