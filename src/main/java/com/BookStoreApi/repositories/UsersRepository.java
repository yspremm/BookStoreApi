package com.BookStoreApi.repositories;

import com.BookStoreApi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findAllById(Long id);
    Optional<Users> findByUsername(String name);
    List<Users> findByAccessToken(String accessToken);
}
