package io.github.capitole.products.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.capitole.products.adapter.db.ProductRepository;
import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.port.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @Override
    public List<Long> findAll() {
        final var list = repository.findAll();
        return list.stream().map(Product::getId).collect(Collectors.toList());
    }
}
