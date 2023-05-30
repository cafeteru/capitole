package io.github.capitole.products.adapter.db;

import java.util.List;

import io.github.capitole.products.adapter.db.model.Product;

public interface ProductRepository {
    List<Product> findAll();
}
