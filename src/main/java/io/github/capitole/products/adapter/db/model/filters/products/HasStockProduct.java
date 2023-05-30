package io.github.capitole.products.adapter.db.model.filters.products;

import java.util.Objects;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;

public class HasStockProduct extends FilterProductAbstract {

    public HasStockProduct(Product product) {
        super(product);
    }

    @Override
    protected boolean checkProduct() {
        return product.getSizes().parallelStream().anyMatch(this::hasStock);
    }

    private boolean hasStock(final Size size) {
        return Objects.nonNull(size.getStock()) && size.getStock().getQuantity() > 0;
    }
}
