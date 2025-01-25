package io.github.cafeteru.capitole.prices.infrastructure.adapter.in.api;

import io.github.cafeteru.capitole.common.util.DateConverter;
import io.github.cafeteru.capitole.prices.application.adapter.in.api.dto.PriceRS;
import io.github.cafeteru.capitole.prices.application.adapters.in.api.PricesApi;
import io.github.cafeteru.capitole.prices.application.port.in.PricePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final PricePort pricePort;
    private final DateConverter dateConverter;

    @Override
    public ResponseEntity<PriceRS> getPrice(
            final String applicationDate, final Integer idProduct, final Integer idBrand) {
        if (isNull(idProduct) || idProduct <= 0 || isNull(idBrand) || idBrand <= 0) {
            throw new IllegalArgumentException("Invalid product or brand");
        }
        final LocalDateTime localDate = dateConverter.stringToLocalDateTime(applicationDate);
        final PriceRS priceRS = pricePort.getPrice(localDate, idProduct, idBrand);
        return Objects.nonNull(priceRS) ?
                ResponseEntity.ok(priceRS) :
                ResponseEntity.notFound().build();
    }
}
