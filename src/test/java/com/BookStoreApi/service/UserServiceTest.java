package com.BookStoreApi.service;

import com.BookStoreApi.model.Users;
import com.BookStoreApi.repositories.OrdersRepository;
import com.BookStoreApi.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    UsersRepository usersRepository;

    @Mock
    OrdersRepository ordersRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(usersRepository, ordersRepository);
    }

        public Optional<Users> optionalUsers() {
        Users user = new Users();
        user.setId(1L);
        user.setAccessToken("461d24f4-5992-4817-a606-8d8eb87da5ac");
        user.setUsername("john");
        user.setPassword("$2a$10$RTSG8pWXJoVw4g031BwcMuujSSdAZC5qIp62iV/qC6Qc7lzavvjCO");
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setDate_of_birth(new Date(1985 - 01 - 15));

        return Optional.of(user);
    }

    public Users users() {
        Users user = new Users();
        user.setId(1L);
        user.setAccessToken("461d24f4-5992-4817-a606-8d8eb87da5ac");
        user.setUsername("john");
        user.setPassword("$2a$10$RTSG8pWXJoVw4g031BwcMuujSSdAZC5qIp62iV/qC6Qc7lzavvjCO");
        user.setFirstname("John");
        user.setLastname("Smith");
        user.setDate_of_birth(new Date(1985 - 01 - 15));

        return user;
    }

    @DisplayName("Test create user success ")
    @Test
    public Users testCreateUsers() throws Exception{
        Users usersResponse = new Users();
        Optional<Users> users = Optional.of(null);

        when(usersRepository.findByUsername("john")).thenReturn(Optional.empty());
        assertEquals("1", users.get().getId());
        assertEquals("461d24f4-5992-4817-a606-8d8eb87da5ac", users.get().getAccessToken());
        assertEquals("john", users.get().getUsername());
        assertEquals("$2a$10$RTSG8pWXJoVw4g031BwcMuujSSdAZC5qIp62iV/qC6Qc7lzavvjCO", users.get().getPassword());
        assertEquals("John", users.get().getFirstname());
        assertEquals("Smith", users.get().getLastname());
//        assertEquals(1985 - 01 - 15, users.get().getDate_of_birth());

        return usersResponse;
    }


}
