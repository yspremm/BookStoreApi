package com.BookStoreApi.service;

import com.BookStoreApi.model.Orders;
import com.BookStoreApi.model.Users;
import com.BookStoreApi.model.response.GetBooksInfo;
import com.BookStoreApi.model.response.GetPrice;
import com.BookStoreApi.repositories.OrdersRepository;
import com.BookStoreApi.repositories.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrdersRepository ordersRepository;
    private UsersRepository usersRepository;
    private static final Logger log = LogManager.getLogger(OrderService.class.getName());

    @Autowired
    public OrderService(OrdersRepository ordersRepository, UsersRepository usersRepository) {
        this.ordersRepository = ordersRepository;
        this.usersRepository = usersRepository;
    }

    public GetPrice createOrders(Orders body, List<GetBooksInfo> listbook) {
        float sum=0;
        int[] OrdersBook = body.getOrders();

        List<Users> user = usersRepository.findByStatus(UserService.UserStatus.ACTIVE);
        body.setUserId(user.get(0).getId());

        for (int i=0;i<OrdersBook.length;i++) {
            sum += listbook.get(OrdersBook[i]-1).getPrice();
        }
        body.setPrice(sum);
        ordersRepository.save(body);
        GetPrice price = new GetPrice();
        price.setPrice(body.getPrice());

        return price;

    }
}
