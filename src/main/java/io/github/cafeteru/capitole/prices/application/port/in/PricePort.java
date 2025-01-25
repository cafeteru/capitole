package io.github.cafeteru.capitole.prices.application.port.in;


import io.github.cafeteru.capitole.prices.application.adapter.in.api.dto.PriceRS;

import java.time.LocalDateTime;


public interface PricePort {

    PriceRS getPrice(final LocalDateTime applicationDate, final Integer productId,
                     final Integer brandId);
}
