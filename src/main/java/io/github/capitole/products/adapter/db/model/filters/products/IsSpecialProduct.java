package io.github.capitole.products.adapter.db.model.filters.products;

import java.util.Objects;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;

public class IsSpecialProduct extends FilterProductAbstract {

    public IsSpecialProduct(Product product) {
        super(product);
    }

    @Override
    protected boolean checkProduct() {
        var sizes = product.getSizes();
        if (sizes.stream().anyMatch(Size::isSpecial)) {
            final var hasSpecial = sizes.parallelStream().filter(Size::isSpecial).anyMatch(this::hasStock);
            final var hasNormal = sizes.parallelStream().filter(size -> !size.isSpecial()).anyMatch(this::hasStock);
            return hasSpecial && hasNormal;
        }
        return false;
    }

    private boolean hasStock(final Size size) {
        return Objects.nonNull(size.getStock()) && size.getStock().getQuantity() > 0;
    }
}
