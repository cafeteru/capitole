package io.github.cafeteru.capitole.prices.infrastructure.adapter.in.api.mapper;

import io.github.cafeteru.capitole.common.util.DateConverter;
import io.github.cafeteru.capitole.prices.application.adapter.in.api.dto.PriceRS;
import io.github.cafeteru.capitole.prices.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceMapperTest {

    private final PriceMapper priceMapper = Mappers.getMapper(PriceMapper.class);

    @Mock
    private DateConverter dateConverter;

    private Price price;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(priceMapper, "dateConverter", dateConverter);

        price = new Price();
        price.setProductId(35455);
        price.setBrandId(1);
        price.setPriceList(1);
        price.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        price.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        price.setPrice(BigDecimal.valueOf(35.50));
    }

    @Test
    void testToPriceRS_WhenPriceIsNull_ShouldReturnNull() {
        final PriceRS priceRS = priceMapper.toPriceRS(null);

        assertNull(priceRS);
    }

    @Test
    void testToPriceRS() {
        when(dateConverter.localDateTimeToString(price.getStartDate())).thenReturn("2023-01-01T00:00:00");
        when(dateConverter.localDateTimeToString(price.getEndDate())).thenReturn("2023-12-31T23:59:59");

        final PriceRS priceRS = priceMapper.toPriceRS(price);

        assertEquals(price.getProductId(), priceRS.getProductId());
        assertEquals(price.getBrandId(), priceRS.getBrandId());
        assertEquals(price.getPriceList(), priceRS.getPriceList());
        assertEquals("2023-01-01T00:00:00", priceRS.getStartDate());
        assertEquals("2023-12-31T23:59:59", priceRS.getEndDate());
        assertEquals(price.getPrice().doubleValue(), priceRS.getFinalPrice());
    }

    @Test
    void testToPriceRS_WhenPriceIsNull_ShouldMapFinalPriceAsNull() {
        when(dateConverter.localDateTimeToString(price.getStartDate())).thenReturn("2023-01-01T00:00:00");
        when(dateConverter.localDateTimeToString(price.getEndDate())).thenReturn("2023-12-31T23:59:59");
        price.setPrice(null);

        final PriceRS priceRS = priceMapper.toPriceRS(price);

        assertEquals(price.getProductId(), priceRS.getProductId());
        assertEquals(price.getBrandId(), priceRS.getBrandId());
        assertEquals(price.getPriceList(), priceRS.getPriceList());
        assertEquals("2023-01-01T00:00:00", priceRS.getStartDate());
        assertEquals("2023-12-31T23:59:59", priceRS.getEndDate());
        assertNull(priceRS.getFinalPrice());
    }
}