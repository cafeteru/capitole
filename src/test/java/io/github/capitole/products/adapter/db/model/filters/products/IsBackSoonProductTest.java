package io.github.capitole.products.adapter.db.model.filters.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;

class IsBackSoonProductTest {
    private Product product;
    private Size size;

    private IsBackSoonProduct isBackSoonProduct;

    @BeforeEach
    void setUp() {
        size = Size.builder()
            .backSoon(true)
            .build();
        product = Product.builder()
            .id(1L)
            .sequence(1)
            .sizes(List.of(size, Size.builder().build()))
            .build();
        isBackSoonProduct = new IsBackSoonProduct(product);
    }

    @Test
    void isValid_with_null_sizes() {
        product.setSizes(null);
        assertFalse(isBackSoonProduct.isValid());
    }

    @Test
    void isValid_with_empty_sizes() {
        product.setSizes(List.of());
        assertFalse(isBackSoonProduct.isValid());
    }

    @Test
    void isValid_without_isBackSoon() {
        size.setBackSoon(false);
        assertFalse(isBackSoonProduct.isValid());
    }

    @Test
    void isValid_with_isBackSoon() {
        assertTrue(isBackSoonProduct.isValid());
    }
}