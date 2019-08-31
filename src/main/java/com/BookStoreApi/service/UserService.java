package com.BookStoreApi.service;

import com.BookStoreApi.model.Users;
import com.BookStoreApi.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UsersRepository usersRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public enum UserStatus {
        ACTIVE, INACTIVE, DELETED;
    }

    public Users createUser(Users body){
        body.setStatus(UserStatus.INACTIVE);
        return usersRepository.save(body);
    }

    public boolean createLogin(Users body){
        List<Users> user = usersRepository.findByUsername(body.getUsername());
        if (user.size() != 0 && body.getPassword().equals(user.get(0).getPassword())) {
            user.get(0).setStatus(UserStatus.ACTIVE);
            usersRepository.save(user.get(0));
            log.info("yes");
            return true;
        } else {
            log.info("no");
            return false;
        }
    }

    public boolean deleteUser(){
        List<Users> user = usersRepository.findByStatus(UserStatus.ACTIVE);
        if (user.size() !=0) {
            usersRepository.delete(user.get(0));
            return true;
        }
        return false;
    }





}
