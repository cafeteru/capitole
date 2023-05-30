package io.github.capitole.products.adapter.db.model.filters.products;

import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;

public class IsBackSoonProduct extends FilterProductAbstract {

    public IsBackSoonProduct(Product product) {
        super(product);
    }

    @Override
    protected boolean checkProduct() {
        return product.getSizes().parallelStream().anyMatch(Size::isBackSoon);
    }
}
