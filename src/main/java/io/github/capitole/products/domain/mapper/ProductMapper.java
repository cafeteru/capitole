package io.github.capitole.products.domain.mapper;

import org.mapstruct.Mapper;

import io.github.capitole.common.mappers.DtoEntityMapper;
import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.domain.model.ProductDto;


@Mapper(componentModel = "spring")
public interface ProductMapper extends DtoEntityMapper<ProductDto, Product> {
}
