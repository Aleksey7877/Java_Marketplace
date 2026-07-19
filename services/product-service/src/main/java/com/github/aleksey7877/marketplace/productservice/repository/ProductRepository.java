package com.github.aleksey7877.marketplace.productservice.repository;

import com.github.aleksey7877.marketplace.productservice.model.Product;
import com.github.aleksey7877.marketplace.productservice.model.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private static final String INSERT_PRODUCT_SQL = """
            insert into products (
                seller_id,
                title,
                description,
                price,
                image_url,
                available_quantity,
                status
            )
            values (?, ?, ?, ?, ?, ?, ?)
            returning
                id,
                seller_id,
                title,
                description,
                price,
                image_url,
                available_quantity,
                status,
                created_at,
                updated_at
            """;

    private static final String FIND_PRODUCT_BY_ID_SQL = """
            select
                id,
                seller_id,
                title,
                description,
                price,
                image_url,
                available_quantity,
                status,
                created_at,
                updated_at
            from products
            where id = ?
            """;

    private final JdbcTemplate jdbcTemplate;

    public Product save(Product product) {
        return jdbcTemplate.queryForObject(
                INSERT_PRODUCT_SQL,
                this::mapRow,
                product.getSellerId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getAvailableQuantity(),
                product.getStatus().name()
        );
    }

    public Optional<Product> findById(Long id) {
        List<Product> products = jdbcTemplate.query(
                FIND_PRODUCT_BY_ID_SQL,
                this::mapRow,
                id
        );

        return products.stream().findFirst();
    }

    private Product mapRow(
            ResultSet resultSet,
            int rowNumber
    ) throws SQLException {
        return Product.builder()
                .id(resultSet.getLong("id"))
                .sellerId(resultSet.getLong("seller_id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .price(resultSet.getBigDecimal("price"))
                .imageUrl(resultSet.getString("image_url"))
                .availableQuantity(
                        resultSet.getInt("available_quantity")
                )
                .status(
                        ProductStatus.valueOf(
                                resultSet.getString("status")
                        )
                )
                .createdAt(
                        resultSet.getTimestamp("created_at").toInstant()
                )
                .updatedAt(
                        resultSet.getTimestamp("updated_at").toInstant()
                )
                .build();
    }
}