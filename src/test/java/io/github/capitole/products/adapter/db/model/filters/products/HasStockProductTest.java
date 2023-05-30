package io.github.capitole.products.adapter.db.model.filters.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;
import io.github.capitole.products.adapter.db.model.Stock;

class HasStockProductTest {
    private Product product;
    private Size size;
    private Stock stock;

    private HasStockProduct hasStockProduct;

    @BeforeEach
    void setUp() {
        stock = Stock.builder()
            .quantity(1L)
            .build();
        size = Size.builder()
            .stock(stock)
            .build();
        product = Product.builder()
            .id(1L)
            .sequence(1)
            .sizes(List.of(size, Size.builder().build()))
            .build();
        hasStockProduct = new HasStockProduct(product);
    }

    @Test
    void isValid_with_null_sizes() {
        product.setSizes(null);
        assertFalse(hasStockProduct.isValid());
    }

    @Test
    void isValid_with_empty_sizes() {
        product.setSizes(List.of());
        assertFalse(hasStockProduct.isValid());
    }

    @Test
    void isValid_with_null_stock() {
        size.setStock(null);
        assertFalse(hasStockProduct.isValid());
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, -1L})
    void isValid_with_invalid_quantity(Long quantity) {
        stock.setQuantity(quantity);
        assertFalse(hasStockProduct.isValid());
    }

    @Test
    void isValid_with_valid_quantity() {
        assertTrue(hasStockProduct.isValid());
    }
}