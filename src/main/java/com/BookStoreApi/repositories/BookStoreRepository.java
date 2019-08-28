package com.BookStoreApi.repositories;

import com.BookStoreApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookStoreRepository extends JpaRepository<User, Long> {
    User findAllById(Long id);
}
