package com.github.aleksey7877.marketplace.productservice;

import com.github.aleksey7877.marketplace.contracts.user.UserServiceGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.grpc.client.ImportGrpcClients;

@SpringBootApplication
@ImportGrpcClients(
        target = "user-service",
        types = UserServiceGrpc.UserServiceBlockingStub.class
)
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                ProductServiceApplication.class,
                args
        );
    }
}