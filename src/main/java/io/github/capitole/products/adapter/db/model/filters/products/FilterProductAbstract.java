package io.github.capitole.products.adapter.db.model.filters.products;

import java.util.Objects;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.filters.Filter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class FilterProductAbstract implements Filter {
    protected final Product product;

    @Override
    public boolean isValid() {
        final var sizes = product.getSizes();
        if (Objects.isNull(sizes) || sizes.isEmpty()) {
            return false;
        }
        return checkProduct();
    }

    protected abstract boolean checkProduct();
}
