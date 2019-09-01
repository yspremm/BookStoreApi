package com.BookStoreApi.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UtilTest {

    @InjectMocks
    Util util;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        util = new Util();
    }

    @DisplayName("Test util")
    @Test
    void testUtil() throws Exception{
        assertEquals(1,1);
    }

}
