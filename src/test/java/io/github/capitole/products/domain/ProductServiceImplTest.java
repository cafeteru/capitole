package io.github.capitole.products.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
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
    void findAll_with_successfully_response() {
        when(repository.findAll()).thenReturn(products);

        var result = service.findAll();

        verify(repository, times(1)).findAll();
        assertNotEquals(products.getClass(), result.getClass());
        assertEquals(products.size(), result.size());
    }

    @Test
    void findAll_with_empty_response() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        var result = service.findAll();

        verify(repository, times(1)).findAll();
        assertTrue(result.isEmpty());
    }

    private Product createProduct(Long id, int sequence) {
        return Product.builder()
            .id(id)
            .sequence(sequence)
            .build();
    }
}