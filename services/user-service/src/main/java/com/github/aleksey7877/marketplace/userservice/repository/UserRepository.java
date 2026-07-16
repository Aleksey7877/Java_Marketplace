package com.github.aleksey7877.marketplace.userservice.repository;

import com.github.aleksey7877.marketplace.userservice.model.User;
import com.github.aleksey7877.marketplace.userservice.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public User save(User user) {
        String sql = """
                insert into users (
                    username,
                    email,
                    password_hash,
                    role
                )
                values (?, ?, ?, ?)
                returning
                    id,
                    username,
                    email,
                    password_hash,
                    role,
                    created_at
                """;

        return jdbcTemplate.queryForObject(
                sql,
                this::mapRow,
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getRole().name()
        );
    }

    private User mapRow(
            ResultSet resultSet,
            int rowNumber
    ) throws SQLException {
        OffsetDateTime createdAt =
                resultSet.getObject(
                        "created_at",
                        OffsetDateTime.class
                );

        return User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .passwordHash(
                        resultSet.getString("password_hash")
                )
                .role(
                        UserRole.valueOf(
                                resultSet.getString("role")
                        )
                )
                .createdAt(createdAt.toInstant())
                .build();
    }
}