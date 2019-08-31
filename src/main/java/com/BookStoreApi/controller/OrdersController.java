package com.BookStoreApi.controller;

import com.BookStoreApi.api.BookStoreApi;
import com.BookStoreApi.model.Orders;
import com.BookStoreApi.model.response.GetBooksInfo;
import com.BookStoreApi.model.response.GetPrice;
import com.BookStoreApi.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookstore")
public class OrdersController {

    private OrderService orderService;
    private BookStoreApi bookStoreApi;
    private static final Logger log = LogManager.getLogger(OrdersController.class.getName());

    @Autowired
    public OrdersController(OrderService orderService,BookStoreApi bookStoreApi) {
        this.orderService = orderService;
        this.bookStoreApi = bookStoreApi;
    }

    @RequestMapping(path = "/users/orders")
    public ResponseEntity<?> createOrders(@Valid @RequestBody Orders body) throws Exception {
        List<GetBooksInfo> listbook = bookStoreApi.getBookInfo();
        GetPrice price = orderService.createOrders(body, (List<GetBooksInfo>) listbook);
        return ResponseEntity.status(HttpStatus.OK).body(price);
    }
}
