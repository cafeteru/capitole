package io.github.cafeteru.capitole.prices.application.port.out;

import io.github.cafeteru.capitole.prices.infrastructure.adapter.out.db.model.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<PriceEntity> getPrice(LocalDateTime dateTime, Integer productId, Integer brandId);
}
