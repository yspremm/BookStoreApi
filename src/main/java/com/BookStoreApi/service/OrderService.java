package com.BookStoreApi.service;

import com.BookStoreApi.model.Orders;
import com.BookStoreApi.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrdersRepository ordersRepository;

    @Autowired
    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Orders createOrders(Orders body) {
        return ordersRepository.save(body);

    }
}
