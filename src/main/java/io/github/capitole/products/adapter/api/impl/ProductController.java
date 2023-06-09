package io.github.capitole.products.adapter.api.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.github.capitole.products.adapter.api.ProductAdapter;
import io.github.capitole.products.port.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductAdapter {
    private final ProductService productService;

    @Override
    public ResponseEntity<List<Long>> findAll() {
        final var list = productService.findAll();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }
}
