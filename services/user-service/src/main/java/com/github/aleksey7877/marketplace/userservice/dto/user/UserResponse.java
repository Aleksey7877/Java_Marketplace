package com.github.aleksey7877.marketplace.userservice.dto.user;

import com.github.aleksey7877.marketplace.userservice.model.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private UserRole role;
    private Instant createdAt;
}