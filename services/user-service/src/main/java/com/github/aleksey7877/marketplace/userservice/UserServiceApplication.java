package com.github.aleksey7877.marketplace.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                UserServiceApplication.class,
                args
        );
    }
}