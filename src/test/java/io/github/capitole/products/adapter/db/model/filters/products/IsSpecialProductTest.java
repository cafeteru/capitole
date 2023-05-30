package io.github.capitole.products.adapter.db.model.filters.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;
import io.github.capitole.products.adapter.db.model.Stock;

class IsSpecialProductTest {
    private Product product;
    private Size specialSize;
    private Size noSpecialSize;
    private Stock specialStock;
    private Stock noSpecialStock;
    private IsSpecialProduct isSpecialProduct;

    @BeforeEach
    void setUp() {
        specialStock = Stock.builder()
            .quantity(1L)
            .build();
        specialSize = Size.builder()
            .special(true)
            .stock(specialStock)
            .build();
        noSpecialStock = Stock.builder()
            .quantity(1L)
            .build();
        noSpecialSize = Size.builder()
            .special(false)
            .stock(noSpecialStock)
            .build();
        product = Product.builder()
            .id(1L)
            .sequence(1)
            .sizes(List.of(specialSize, noSpecialSize))
            .build();
        isSpecialProduct = new IsSpecialProduct(product);
    }

    @Test
    void isValid_with_null_sizes() {
        product.setSizes(null);
        assertFalse(isSpecialProduct.isValid());
    }

    @Test
    void isValid_with_empty_sizes() {
        product.setSizes(List.of());
        assertFalse(isSpecialProduct.isValid());
    }

    @Test
    void isValid_without_isBackSoon() {
        specialSize.setSpecial(false);
        assertFalse(isSpecialProduct.isValid());
    }

    @Test
    void isValid_with_all_isBackSoon() {
        noSpecialSize.setSpecial(true);
        assertFalse(isSpecialProduct.isValid());
    }

    @Test
    void isValid_with_special() {
        assertTrue(isSpecialProduct.isValid());
    }

    @Test
    void isValid_with_no_stock_and_special() {
        specialStock.setQuantity(0L);
        assertFalse(isSpecialProduct.isValid());
    }

    @Test
    void isValid_with_no_stock_and_no_special() {
        noSpecialStock.setQuantity(0L);
        assertFalse(isSpecialProduct.isValid());
    }

    @Test
    void isValid_with_no_stock_and_no_special_and_no_stock() {
        specialStock.setQuantity(0L);
        noSpecialStock.setQuantity(0L);
        assertFalse(isSpecialProduct.isValid());
    }

    @Test
    void isValid_with_one_size() {
        product.setSizes(List.of(specialSize));
        assertFalse(isSpecialProduct.isValid());
    }

}