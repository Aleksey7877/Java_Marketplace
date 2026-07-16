package com.github.aleksey7877.marketplace.userservice.controller;

import com.github.aleksey7877.marketplace.userservice.service.DatabaseHealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {

    private final DatabaseHealthService databaseHealthService;

    @GetMapping
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    @GetMapping("/database")
    public ResponseEntity<Map<String, String>> databaseHealth() {
        boolean available = databaseHealthService.isDatabaseAvailable();

        HttpStatus status = available ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE;

        Map<String, String> response = Map.of("database", available ? "UP" : "DOWN");

        return ResponseEntity.status(status).body(response);
    }
}