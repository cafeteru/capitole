package io.github.capitole.products.domain;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.capitole.products.adapter.db.ProductRepository;
import io.github.capitole.products.adapter.db.model.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private List<Product> products;

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        products = List.of(
            createProduct(100L, 4),
            createProduct(50L, 35)
        );
    }

    @Test
    void findAll() {
    }

    private Product createProduct(Long id, int sequence) {
        return Product.builder()
            .id(id)
            .sequence(sequence)
            .build();
    }
}