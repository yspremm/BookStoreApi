package com.BookStoreApi.controller;

import com.BookStoreApi.api.BookStoreApi;
import com.BookStoreApi.constants.OrdersConstants;
import com.BookStoreApi.exception.OrdersException;
import com.BookStoreApi.model.Orders;
import com.BookStoreApi.model.StatusModel;
import com.BookStoreApi.model.response.GetBooksInfo;
import com.BookStoreApi.model.response.GetPrice;
import com.BookStoreApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookstore")
public class OrdersController {

    private OrderService orderService;
    private BookStoreApi bookStoreApi;
//    private static final Logger log = LogManager.getLogger(OrdersController.class.getName());

    @Autowired
    public OrdersController(OrderService orderService,BookStoreApi bookStoreApi) {
        this.orderService = orderService;
        this.bookStoreApi = bookStoreApi;
    }
//
    @PostMapping(path = "/users/orders")
    public ResponseEntity<?> createOrders(@Valid @RequestBody Orders body , @RequestHeader String access_token) throws Exception {
        try{
            List<GetBooksInfo> listbook = bookStoreApi.getBookInfo();
            GetPrice price = orderService.createOrders(body, (List<GetBooksInfo>) listbook, access_token);
            return ResponseEntity.status(HttpStatus.OK).body(price);
        } catch (OrdersException e) {
            OrdersConstants ordersConstants = e.getOrdersConstants();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusModel(ordersConstants.getMessage()));
        }

    }
}
