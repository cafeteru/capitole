package io.github.capitole.products.adapter.db.model.filters.products;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;
import io.github.capitole.products.adapter.db.model.filters.Filter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IsSpecialProduct implements Filter {
    private final Product product;

    @Override
    public boolean isValid() {
        final var sizes = product.getSizes();
        if (sizes.parallelStream().anyMatch(Size::isSpecial)) {
            final var hasSpecial = sizes.parallelStream().anyMatch(size ->
                size.isSpecial() && new HasStockProduct(product).isValid());
            final var hasNormal = sizes.parallelStream().anyMatch(size ->
                !size.isSpecial() && new HasStockProduct(product).isValid());
            return hasSpecial && hasNormal;
        }
        return false;
    }
}
