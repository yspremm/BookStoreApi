package com.BookStoreApi.service;

import com.BookStoreApi.constants.OrdersConstants;
import com.BookStoreApi.exception.OrdersException;
import com.BookStoreApi.model.Orders;
import com.BookStoreApi.model.Users;
import com.BookStoreApi.model.response.GetBooksInfo;
import com.BookStoreApi.model.response.GetPrice;
import com.BookStoreApi.repositories.OrdersRepository;
import com.BookStoreApi.repositories.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrdersRepository ordersRepository;
    private UsersRepository usersRepository;
//    private static final Logger log = LogManager.getLogger(OrderService.class.getName());

    @Autowired
    public OrderService(OrdersRepository ordersRepository, UsersRepository usersRepository) {
        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
    }

    public GetPrice createOrders(Orders body, List<GetBooksInfo> listbook, String getToken) throws OrdersException {
        float sum=0;
        String token = getToken.substring(7);
        int[] OrdersBook = body.getOrders();
        GetPrice price = new GetPrice();
        List<Users> user = usersRepository.findByAccessToken(token);
        body.setUserId(user.get(0).getId());
        for (int i=0;i<OrdersBook.length;i++) {
            if (OrdersBook[i] <= listbook.size()) {
                sum += listbook.get(OrdersBook[i]-1).getPrice();
            }else {
                throw new OrdersException(OrdersConstants.ORDER_NOT_FOUND, HttpStatus.BAD_REQUEST);
            }

        }
        body.setPrice(sum);
        ordersRepository.save(body);
        price.setPrice(body.getPrice());

        return price;

    }
}
