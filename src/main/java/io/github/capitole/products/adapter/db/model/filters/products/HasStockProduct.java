package io.github.capitole.products.adapter.db.model.filters.products;

import java.util.Objects;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;
import io.github.capitole.products.adapter.db.model.filters.Filter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HasStockProduct implements Filter {
    private final Product product;

    @Override
    public boolean isValid() {
        final var sizes = product.getSizes();
        if (Objects.isNull(sizes) || sizes.isEmpty()) {
            return false;
        }
        return sizes.parallelStream().anyMatch(this::hasStock);
    }

    private boolean hasStock(final Size size) {
        return Objects.nonNull(size.getStock()) && size.getStock().getQuantity() > 0;
    }
}
