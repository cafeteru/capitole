package io.github.capitole.products.domain;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import io.github.capitole.products.adapter.db.ProductRepository;
import io.github.capitole.products.domain.mapper.ProductMapper;
import io.github.capitole.products.domain.model.ProductDto;
import io.github.capitole.products.port.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<ProductDto> findAll() {
        final var list = repository.findAll();
        return mapper.toDtos(list);
    }
}
