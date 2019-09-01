package com.BookStoreApi.exception;

import com.BookStoreApi.constants.OrdersConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class OrdersException extends Exception {

    private OrdersConstants ordersConstants;
    private HttpStatus httpStatus;

    public OrdersException(OrdersConstants ordersConstants, HttpStatus httpStatus){
        this.ordersConstants = ordersConstants;
        this.httpStatus = httpStatus;
    }
}
