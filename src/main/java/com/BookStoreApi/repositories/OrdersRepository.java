package com.BookStoreApi.repositories;

import com.BookStoreApi.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByOrders(int[] orders);
}