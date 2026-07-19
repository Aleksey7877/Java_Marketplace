package com.github.aleksey7877.marketplace.productservice.service;

import com.github.aleksey7877.marketplace.productservice.repository.DatabaseHealthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseHealthService {

    private final DatabaseHealthRepository databaseHealthRepository;

    public boolean isDatabaseAvailable() {
        try {
            Integer result = databaseHealthRepository.ping();
            return Integer.valueOf(1).equals(result);
        } catch (DataAccessException exception) {
            return false;
        }
    }
}