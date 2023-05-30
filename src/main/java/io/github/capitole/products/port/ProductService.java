package io.github.capitole.products.port;

import java.util.List;

import io.github.capitole.products.domain.model.ProductDto;

public interface ProductService {
    List<ProductDto> findAll();
}
