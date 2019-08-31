package com.BookStoreApi.controller;

import com.BookStoreApi.model.Orders;
import com.BookStoreApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/bookstore")
public class OrdersController {

    private OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/users/orders")
    public ResponseEntity<?> createOrders(@Valid @RequestBody Orders body){
        Orders orders = orderService.createOrders(body);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
