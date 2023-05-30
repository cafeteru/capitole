package io.github.capitole.products.adapter.db.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductRepositoryImplTest {
    private ProductRepositoryImpl productRepositoryImpl;

    @BeforeEach
    void setUp() {
        productRepositoryImpl = new ProductRepositoryImpl();
    }

    @Test
    void findAll() {
        var products = productRepositoryImpl.findAll();
        assertFalse(products.isEmpty());
    }

}