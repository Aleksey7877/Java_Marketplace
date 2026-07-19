package com.github.aleksey7877.marketplace.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        properties = "spring.grpc.server.port=0"
)
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}