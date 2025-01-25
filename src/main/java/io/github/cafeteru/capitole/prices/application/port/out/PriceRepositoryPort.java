package io.github.cafeteru.capitole.prices.application.port.out;

import io.github.cafeteru.capitole.prices.infrastructure.adapter.out.db.model.PriceEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepositoryPort {
    List<PriceEntity> getPrice(LocalDateTime dateTime, Integer productId, Integer brandId);
}
