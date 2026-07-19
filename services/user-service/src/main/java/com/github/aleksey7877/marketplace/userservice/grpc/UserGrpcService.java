package com.github.aleksey7877.marketplace.userservice.grpc;

import com.github.aleksey7877.marketplace.contracts.user.GetUserRequest;
import com.github.aleksey7877.marketplace.contracts.user.GetUserResponse;
import com.github.aleksey7877.marketplace.contracts.user.UserRole;
import com.github.aleksey7877.marketplace.contracts.user.UserServiceGrpc;
import com.github.aleksey7877.marketplace.userservice.dto.user.UserResponse;
import com.github.aleksey7877.marketplace.userservice.exception.UserNotFoundException;
import com.github.aleksey7877.marketplace.userservice.service.UserService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;


@GrpcService
@RequiredArgsConstructor
public class UserGrpcService
        extends UserServiceGrpc.UserServiceImplBase {

    private final UserService userService;

    @Override
    public void getUser(
            GetUserRequest request,
            StreamObserver<GetUserResponse> responseObserver
    ) {
        long id = request.getUserId();

        try {
            UserResponse user = userService.findById(id);

            GetUserResponse response = GetUserResponse.newBuilder()
                    .setId(user.getId())
                    .setUsername(user.getUsername())
                    .setEmail(user.getEmail())
                    .setRole(mapRole(user.getRole()))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (UserNotFoundException exception) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription(exception.getMessage())
                            .asRuntimeException()
            );
        }
    }

    private com.github.aleksey7877.marketplace.contracts.user.UserRole mapRole(
            com.github.aleksey7877.marketplace.userservice.model.UserRole role
    ) {
        return switch (role) {
            case BUYER -> UserRole.USER_ROLE_BUYER;
            case SELLER -> UserRole.USER_ROLE_SELLER;
            case ADMIN -> UserRole.USER_ROLE_ADMIN;
        };
    }
}