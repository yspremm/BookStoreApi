package com.BookStoreApi.service;

import com.BookStoreApi.constants.UsersConstants;
import com.BookStoreApi.exception.UsersException;
import com.BookStoreApi.model.Orders;
import com.BookStoreApi.model.Role;
import com.BookStoreApi.model.Users;
import com.BookStoreApi.model.response.AccessTokenResponse;
import com.BookStoreApi.model.response.GetUserInfo;
import com.BookStoreApi.repositories.OrdersRepository;
import com.BookStoreApi.repositories.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(Users body) throws UsersException {
        Optional<Users> optional = usersRepository.findByUsername(body.getUsername());

        if(! optional.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(body.getPassword());
            body.setPassword(hashedPassword);

            body.setRoles(Arrays.asList(new Role("USER"), new Role("ACTUATOR")));
            body.setActive(true);

            usersRepository.save(body);
        } else {
            throw new UsersException(UsersConstants.USER_EXIST, HttpStatus.BAD_REQUEST);
        }
    }

    public void requestLogin(Users body, AccessTokenResponse token) throws UsersException {
        Optional<Users> users = usersRepository.findByUsername(body.getUsername());
        BCryptPasswordEncoder passwordFromBody = new BCryptPasswordEncoder();
        boolean booleanhashed = passwordFromBody.matches(body.getPassword(), users.get().getPassword());
        if (users.isPresent() && booleanhashed) {
            users.get().setAccessToken(token.getAccess_token());
            usersRepository.save(users.get());
        } else {
            throw new UsersException(UsersConstants.USER_OR_PASSWORD_INCORRECT, HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteUser(String getToken) throws UsersException {
        Users usersResponse = new Users();
        String token = getToken.substring(7);
        List<Users> user = usersRepository.findByAccessToken(token);
        log.info(user.toString());
        if (user.get(0).getId() != null) {
            usersRepository.delete(user.get(0));
        } else {
            throw new UsersException(UsersConstants.NOT_DELETE, HttpStatus.BAD_REQUEST);
        }
    }

    public GetUserInfo getUserInfo(String getToken) throws UsersException {
        String token = getToken.substring(7);
        GetUserInfo getUserInfo = new GetUserInfo();
        List<Users> user = usersRepository.findByAccessToken(token);
        if (!user.isEmpty()) {
            Orders order = ordersRepository.findByUserId(user.get(0).getId());
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
