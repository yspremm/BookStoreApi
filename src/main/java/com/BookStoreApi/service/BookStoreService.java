package com.BookStoreApi.service;

import com.BookStoreApi.model.User;
import com.BookStoreApi.repositories.BookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookStoreService {

    private BookStoreRepository bookStoreRepository;

    @Autowired
    public BookStoreService(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    public User createUser(User body){
        return bookStoreRepository.save(body);
    }
}
