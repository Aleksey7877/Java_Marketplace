package com.github.aleksey7877.marketplace.productservice.dto;

import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequest {

    @NotNull(message = "sellerId must not be null")
    @Positive(message = "sellerId must be positive")
    private Long sellerId;

    @NotBlank(message = "title must not be blank")
    @Size(max = 200, message = "title length must not exceed 200 characters")
    private String title;

    @NotBlank(message = "description must not be blank")
    private String description;

    @NotNull(message = "price must not be null")
    @DecimalMin(
            value = "0.01",
            message = "price must be greater than or equal to 0.01"
    )
    private BigDecimal price;

    @NotBlank(message = "imageUrl must not be blank")
    @Size(
            max = 2048,
            message = "imageUrl length must not exceed 2048 characters"
    )
    @URL(message = "imageUrl must be a valid URL")
    private String imageUrl;

    @NotNull(message = "availableQuantity must not be null")
    @PositiveOrZero(message = "availableQuantity must be zero or positive")
    private Integer availableQuantity;
}