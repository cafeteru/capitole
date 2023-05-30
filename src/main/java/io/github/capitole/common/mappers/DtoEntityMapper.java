package io.github.capitole.common.mappers;


import java.util.List;

public interface DtoEntityMapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDtos(List<E> entities);

    List<E> toEntities(List<D> dtos);

}
