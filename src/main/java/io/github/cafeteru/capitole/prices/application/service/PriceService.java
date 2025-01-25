package io.github.cafeteru.capitole.prices.application.service;


import io.github.cafeteru.capitole.prices.application.adapter.in.api.dto.PriceRS;
import io.github.cafeteru.capitole.prices.application.port.in.PricePort;
import io.github.cafeteru.capitole.prices.application.port.out.PriceRepositoryPort;
import io.github.cafeteru.capitole.prices.domain.Price;
import io.github.cafeteru.capitole.prices.infrastructure.adapter.in.api.mapper.PriceMapper;
import io.github.cafeteru.capitole.prices.infrastructure.adapter.out.db.mapper.PriceEntityMapper;
import io.github.cafeteru.capitole.prices.infrastructure.adapter.out.db.model.PriceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService implements PricePort {

    private final PriceRepositoryPort repository;
    private final PriceEntityMapper priceEntityMapper;
    private final PriceMapper priceMapper;

    @Override
    public PriceRS getPrice(
            final LocalDateTime applicationDate, final Integer productId, final Integer brandId) {
        final List<PriceEntity> priceEntities = repository.getPrice(applicationDate, productId, brandId);
        final List<Price> prices = priceEntities.stream()
                .map(priceEntityMapper::toPrice)
                .toList();
        final Optional<Price> result = prices.stream()
                .max(Comparator.comparing(Price::getPriority));
        return result.map(priceMapper::toPriceRS)
                .orElse(null);
    }
}
