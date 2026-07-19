package com.github.aleksey7877.marketplace.productservice.client;

import com.github.aleksey7877.marketplace.contracts.user.GetUserRequest;
import com.github.aleksey7877.marketplace.contracts.user.GetUserResponse;
import com.github.aleksey7877.marketplace.contracts.user.UserRole;
import com.github.aleksey7877.marketplace.contracts.user.UserServiceGrpc;
import com.github.aleksey7877.marketplace.productservice.exception.SellerNotFoundException;
import com.github.aleksey7877.marketplace.productservice.exception.UserIsNotSellerException;
import com.github.aleksey7877.marketplace.productservice.exception.UserServiceUnavailableException;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class UserGrpcClient {

    private static final long DEADLINE_SECONDS = 2;

    private final UserServiceGrpc.UserServiceBlockingStub userStub;

    public GetUserResponse getUser(long userId) {
        GetUserRequest request = GetUserRequest.newBuilder()
                .setUserId(userId)
                .build();

        try {
            return userStub
                    .withDeadlineAfter(
                            DEADLINE_SECONDS,
                            TimeUnit.SECONDS
                    )
                    .getUser(request);
        } catch (StatusRuntimeException exception) {
            throw translateException(userId, exception);
        }
    }

    public void validateSeller(long sellerId) {
        GetUserResponse user = getUser(sellerId);

        if (user.getRole() != UserRole.USER_ROLE_SELLER) {
            throw new UserIsNotSellerException(sellerId);
        }
    }

    private RuntimeException translateException(
            long userId,
            StatusRuntimeException exception
    ) {
        return switch (exception.getStatus().getCode()) {
            case NOT_FOUND ->
                    new SellerNotFoundException(userId);

            case UNAVAILABLE ->
                    new UserServiceUnavailableException(
                            "user-service is unavailable",
                            exception
                    );

            case DEADLINE_EXCEEDED ->
                    new UserServiceUnavailableException(
                            "user-service did not respond within "
                                    + DEADLINE_SECONDS
                                    + " seconds",
                            exception
                    );

            default ->
                    new UserServiceUnavailableException(
                            "unexpected gRPC error from user-service: "
                                    + exception.getStatus().getCode(),
                            exception
                    );
        };
    }
}