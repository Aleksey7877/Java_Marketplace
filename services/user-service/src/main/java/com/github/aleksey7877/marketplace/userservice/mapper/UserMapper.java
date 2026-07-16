package com.github.aleksey7877.marketplace.userservice.mapper;

import com.github.aleksey7877.marketplace.userservice.dto.user.CreateUserRequest;
import com.github.aleksey7877.marketplace.userservice.dto.user.UserResponse;
import com.github.aleksey7877.marketplace.userservice.model.User;
import com.github.aleksey7877.marketplace.userservice.model.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toModel(
            CreateUserRequest request,
            String passwordHash
    ) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordHash)
                .role(UserRole.valueOf(request.getRole().name()))
                .build();
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}