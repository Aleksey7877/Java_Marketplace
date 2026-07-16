package com.github.aleksey7877.marketplace.userservice.service;

import com.github.aleksey7877.marketplace.userservice.dto.user.CreateUserRequest;
import com.github.aleksey7877.marketplace.userservice.dto.user.UserResponse;
import com.github.aleksey7877.marketplace.userservice.mapper.UserMapper;
import com.github.aleksey7877.marketplace.userservice.model.User;
import com.github.aleksey7877.marketplace.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(CreateUserRequest request) {
        String passwordHash =
                passwordEncoder.encode(request.getPassword());

        User user = userMapper.toModel(
                request,
                passwordHash
        );

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
}