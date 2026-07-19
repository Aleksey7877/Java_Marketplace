package com.github.aleksey7877.marketplace.productservice.service;

import com.github.aleksey7877.marketplace.productservice.exception.ProductNotFoundException;
import com.github.aleksey7877.marketplace.productservice.client.UserGrpcClient;
import com.github.aleksey7877.marketplace.productservice.dto.CreateProductRequest;
import com.github.aleksey7877.marketplace.productservice.dto.ProductResponse;
import com.github.aleksey7877.marketplace.productservice.model.Product;
import com.github.aleksey7877.marketplace.productservice.model.ProductStatus;
import com.github.aleksey7877.marketplace.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final UserGrpcClient userGrpcClient;
    private final ProductRepository productRepository;

    public ProductResponse create(CreateProductRequest request) {
        userGrpcClient.validateSeller(request.getSellerId());

        Product productToSave = Product.builder()
                .sellerId(request.getSellerId())
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .availableQuantity(request.getAvailableQuantity())
                .status(ProductStatus.DRAFT)
                .build();

        Product savedProduct =
                productRepository.save(productToSave);

        return toResponse(savedProduct);
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(id)
                );

        return toResponse(product);
    }

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .availableQuantity(product.getAvailableQuantity())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}