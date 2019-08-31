package com.BookStoreApi.service;

import com.BookStoreApi.constants.UsersConstants;
import com.BookStoreApi.exception.UsersException;
import com.BookStoreApi.model.Orders;
import com.BookStoreApi.model.Users;
import com.BookStoreApi.model.response.GetUserInfo;
import com.BookStoreApi.repositories.OrdersRepository;
import com.BookStoreApi.repositories.UsersRepository;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UsersRepository usersRepository;
    private OrdersRepository ordersRepository;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UsersRepository usersRepository,OrdersRepository ordersRepository) {
        this.usersRepository = usersRepository;
        this.ordersRepository = ordersRepository;
    }

    public enum UserStatus {
        ACTIVE, INACTIVE, DELETED;
    }

    public Users createUser(Users body) throws UsersException {
        Users usersResponse = new Users();
        List<Users> users = usersRepository.findByUsername(body.getUsername());
        if(users.size() == 0 && body.getUsername() != null &&body.getPassword() != null) {
            body.setStatus(UserStatus.INACTIVE);
            usersRepository.save(body);
        } else {
            throw new UsersException(UsersConstants.USER_EXIST, HttpStatus.BAD_REQUEST);
        }
        return usersResponse;
    }

    public Users requestLogin(Users body) throws UsersException {
        Users usersResponse = new Users();
        List<Users> users = usersRepository.findByUsername(body.getUsername());
        if (users.size() != 0 && body.getPassword().equals(users.get(0).getPassword())) {
            users.get(0).setStatus(UserStatus.ACTIVE);
            usersRepository.save(users.get(0));
        } else {
            throw new UsersException(UsersConstants.USER_OR_PASSWORD_INCORRECT, HttpStatus.BAD_REQUEST);
        }
        return usersResponse;
    }

    public Users deleteUser() throws UsersException {
        Users usersResponse = new Users();
        List<Users> user = usersRepository.findByStatus(UserStatus.ACTIVE);
        if (user.size() !=0) {
            usersRepository.delete(user.get(0));
        } else {
            throw new UsersException(UsersConstants.NOT_DELETE, HttpStatus.BAD_REQUEST);
        }
        return usersResponse;
    }

    public GetUserInfo getUserInfo() throws UsersException {
        List<Users> user = usersRepository.findByStatus(UserStatus.ACTIVE);
        Orders order = ordersRepository.findByUserId(user.get(0).getId());
        GetUserInfo getUserInfo = new GetUserInfo();
        if(user.size() !=0){
            getUserInfo.setFirstname(user.get(0).getFirstname());
            getUserInfo.setLastname(user.get(0).getLastname());
            getUserInfo.setDate_of_birth(user.get(0).getDate_of_birth());
            getUserInfo.setOrders(order.getOrders());
        } else {
            throw new UsersException(UsersConstants.NOT_GET_USER_INFO, HttpStatus.BAD_REQUEST);
        }

        return getUserInfo;
    }





}
