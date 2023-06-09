package io.github.capitole.products.adapter.api.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import org.springframework.http.HttpStatus;

import io.github.capitole.products.port.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    private List<Long> productIds;
    @InjectMocks
    private ProductController productController;
    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productIds = List.of(1L, 2L, 3L, 4L, 5L);
    }

    @Test
    void findAll_with_successfully_response() {
        when(productService.findAll()).thenReturn(productIds);

        var result = productController.findAll();

        verify(productService, times(1)).findAll();
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(productIds, result.getBody());
    }

    @Test
    void findAll_with_empty_response() {
        when(productService.findAll()).thenReturn(Collections.emptyList());

        var result = productController.findAll();

        verify(productService, times(1)).findAll();
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());
    }
}