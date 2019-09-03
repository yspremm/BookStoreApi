package com.BookStoreApi.service;

import com.BookStoreApi.exception.OrdersException;
import com.BookStoreApi.model.Orders;
import com.BookStoreApi.model.Users;
import com.BookStoreApi.model.response.GetBooksInfo;
import com.BookStoreApi.model.response.GetPrice;
import com.BookStoreApi.repositories.OrdersRepository;
import com.BookStoreApi.repositories.UsersRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private UsersRepository usersRepository;

    private static final Logger log = LogManager.getLogger(OrderServiceTest.class.getName());

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(ordersRepository, usersRepository);
    }
    public enum status {
        ACTIVE, INACTIVE, DELETED;
    }

    public Orders ordersList() {
        Orders order = new Orders();
        order.setId(1L);
        order.setUserId(1L);
        order.setOrders(new int[]{1, 8});
        order.setPrice((float) 630.06);

        return order;
    }

    public List<Users> usersList() {
        List<Users> users = new ArrayList<>();
        Users user = new Users();
        user.setId(1L);
        user.setUsername("john");
        user.setPassword("thisismysecret");
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setDate_of_birth(new Date(1985-01-15));
        user.setStatus(status.ACTIVE);
        users.add(user);

        user = new Users();
        user.setId(2L);
        user.setUsername("johndef");
        user.setPassword("thisismysecret");
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setDate_of_birth(new Date(1985-01-15));
        user.setStatus(status.ACTIVE);
        users.add(user);

        return users;
    }

    public List<GetBooksInfo> bookList() {
        List<GetBooksInfo> getBooksInfos = new ArrayList<>();
        GetBooksInfo getBooksInfo = new GetBooksInfo();
        getBooksInfo.setId(1L);
        getBooksInfo.setAuthor_name("Lisa Wingate");
        getBooksInfo.setBook_name("Before We Were Yours: A Novel");
        getBooksInfo.setPrice((float) 340.0);
        getBooksInfo.setIsRecommended(false);
        getBooksInfos.add(getBooksInfo);

        getBooksInfo = new GetBooksInfo();
        getBooksInfo.setId(2L);
        getBooksInfo.setAuthor_name("Barbara Davis");
        getBooksInfo.setBook_name("When Never Comes");
        getBooksInfo.setPrice((float) 179.0);
        getBooksInfo.setIsRecommended(false);
        getBooksInfos.add(getBooksInfo);

        return getBooksInfos;
    }

    @DisplayName("test create orders should return price")
    @Test
    public void testCreateOrders() throws OrdersException {
        List<Users> users = usersList();
        Orders orders = ordersList();

        when(usersRepository.findByStatus(status.ACTIVE)).thenReturn(users);
        assertEquals(orders.getUserId(), users.get(0).getId());
        GetPrice resp = orderService.createOrders(ordersList(), bookList());
        assertEquals(users.get(0), resp.getPrice());
//        assertEquals("340.0", resp.getPrice().toString());


    }
}
