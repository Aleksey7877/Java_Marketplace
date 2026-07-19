package com.github.aleksey7877.marketplace.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private Long sellerId;
    private String title;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Integer availableQuantity;
    private ProductStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}