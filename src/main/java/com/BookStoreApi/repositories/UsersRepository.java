package com.BookStoreApi.repositories;

import com.BookStoreApi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findAllById(Long id);
    List<Users> findByUsername(String name);
    List<Users> findByStatus(Enum status);
}
