package com.github.aleksey7877.marketplace.userservice.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "username must not be blank")
    @Size(
            min = 3,
            max = 50,
            message = "username length must be between 3 and 50"
    )
    @Pattern(
            regexp = "^[a-zA-Z0-9_]+$",
            message = "username may contain only letters, numbers and underscore"
    )
    private String username;

    @NotBlank(message = "email must not be blank")
    @Email(message = "email has invalid format")
    @Size(
            max = 255,
            message = "email length must not exceed 255"
    )
    private String email;

    @NotBlank(message = "password must not be blank")
    @Size(
            min = 8,
            max = 64,
            message = "password length must be between 8 and 64"
    )
    private String password;

    @NotNull(message = "role must not be null")
    private RegistrationRole role;
}