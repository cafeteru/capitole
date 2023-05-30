package io.github.capitole.products.adapter.db.model.filters.products;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;
import io.github.capitole.products.adapter.db.model.filters.Filter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IsBackSoonProduct implements Filter {
    private final Product product;

    @Override
    public boolean isValid() {
        return product.getSizes().parallelStream().anyMatch(Size::isBackSoon);
    }
}
