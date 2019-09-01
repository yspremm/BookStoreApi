package com.BookStoreApi.constants;

import lombok.Getter;

@Getter
public enum OrdersConstants {
    ORDER_NOT_FOUND("the order invalid");


    private String message;

    OrdersConstants(String message) {
        this.message = message;
    }

}
