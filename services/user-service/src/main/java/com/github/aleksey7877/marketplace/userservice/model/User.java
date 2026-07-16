package com.github.aleksey7877.marketplace.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String email;
    private String passwordHash;
    private UserRole role;
    private Instant createdAt;
}