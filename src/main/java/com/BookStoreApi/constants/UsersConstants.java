package com.BookStoreApi.constants;

import lombok.Getter;

@Getter
public enum UsersConstants {
    CREATE_SUCCESS("create user account success"),
    LOGIN_SUCCESS("login successfully"),
    DELETE_SUCCESS("account deleted"),

    USER_EXIST("cannot create user account, username exists"),
    USER_OR_PASSWORD_INCORRECT("username or password incorrect"),
    NOT_DELETE("cannot delete"),
    NOT_GET_USER_INFO("cannot gets information about the logged in user"),
    GET_NULL_INFO("Enter a username or password");


    private String message;

    UsersConstants(String message) {
        this.message = message;
    }
}
