package com.BookStoreApi.repositories;

import com.BookStoreApi.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Orders findByUserId(Long userId);

}