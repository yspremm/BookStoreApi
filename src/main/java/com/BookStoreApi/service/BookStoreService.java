package com.BookStoreApi.service;

import com.BookStoreApi.model.User;
import com.BookStoreApi.repositories.BookStoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreService {

    private BookStoreRepository bookStoreRepository;
    private static final Logger log = LoggerFactory.getLogger(BookStoreService.class);

    @Autowired
    public BookStoreService(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    public enum UserStatus {
        PENDING, ACTIVE, INACTIVE, DELETED;
    }

    public User createUser(User body){
        body.setStatus(UserStatus.INACTIVE);
        return bookStoreRepository.save(body);
    }

    public boolean createLogin(User body){
        List<User> user = bookStoreRepository.findByUsername(body.getUsername());
        if (user.size() != 0 && body.getPassword().equals(user.get(0).getPassword())) {
            user.get(0).setStatus(UserStatus.ACTIVE);
            bookStoreRepository.save(user.get(0));
            log.info("yes");
            return true;
        } else {
            log.info("no");
            return false;
        }
    }

    public boolean deleteUser(){
        List<User> user = bookStoreRepository.findByStatus(UserStatus.ACTIVE);
        if (user.size() !=0) {
            bookStoreRepository.delete(user.get(0));
            return true;
        }
        return false;
    }




}
