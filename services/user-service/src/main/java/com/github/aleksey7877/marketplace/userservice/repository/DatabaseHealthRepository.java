package com.github.aleksey7877.marketplace.userservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DatabaseHealthRepository {

    private final JdbcTemplate jdbcTemplate;

    public Integer ping() {
        return jdbcTemplate.queryForObject(
                "SELECT 1",
                Integer.class
        );
    }
}