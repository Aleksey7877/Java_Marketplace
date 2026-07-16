package com.github.aleksey7877.marketplace.userservice.controller;

import com.github.aleksey7877.marketplace.userservice.dto.user.CreateUserRequest;
import com.github.aleksey7877.marketplace.userservice.dto.user.UserResponse;
import com.github.aleksey7877.marketplace.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Valid @RequestBody CreateUserRequest request
    ) {
        UserResponse response =
                userService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable long id
    ) {
        UserResponse response = userService.findById(id);

        return ResponseEntity.ok(response);
    }
}