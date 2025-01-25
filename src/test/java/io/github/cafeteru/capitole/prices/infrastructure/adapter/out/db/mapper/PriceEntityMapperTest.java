package io.github.cafeteru.capitole.prices.infrastructure.adapter.out.db.mapper;

import io.github.cafeteru.capitole.prices.domain.Price;
import io.github.cafeteru.capitole.prices.infrastructure.adapter.out.db.model.PriceEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceEntityMapperTest {

    private final PriceEntityMapper mapper = Mappers.getMapper(PriceEntityMapper.class);

    @Test
    void when_toPrice_givenValidPriceEntity_shouldReturnMappedPrice() {
        final PriceEntity priceEntity = PriceEntity.builder()
                .id(1L)
                .brandId(1)
                .startDate(LocalDateTime.of(2023, 1, 1, 10, 0))
                .endDate(LocalDateTime.of(2023, 1, 1, 20, 0))
                .priceList(2)
                .productId(101)
                .priority(1)
                .price(BigDecimal.valueOf(99.99))
                .curr("EUR")
                .build();

        final Price price = mapper.toPrice(priceEntity);

        assertNotNull(price);
        assertEquals(priceEntity.getId(), price.getId());
        assertEquals(priceEntity.getBrandId(), price.getBrandId());
        assertEquals(priceEntity.getStartDate(), price.getStartDate());
        assertEquals(priceEntity.getEndDate(), price.getEndDate());
        assertEquals(priceEntity.getPriceList(), price.getPriceList());
        assertEquals(priceEntity.getProductId(), price.getProductId());
        assertEquals(priceEntity.getPriority(), price.getPriority());
        assertEquals(priceEntity.getPrice(), price.getPrice());
        assertEquals(priceEntity.getCurr(), price.getCurr());
    }

    @Test
    void when_toPrice_givenNullEntity_shouldReturnNull() {
        final Price price = mapper.toPrice(null);

        assertNull(price);
    }
}
