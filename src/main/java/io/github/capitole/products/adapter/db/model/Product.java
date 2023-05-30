package io.github.capitole.products.adapter.db.model;

import static io.github.capitole.products.adapter.db.util.CsvUtils.cleanString;

import java.util.ArrayList;
import java.util.List;

import io.github.capitole.products.adapter.db.model.filters.AnyCompositeFilter;
import io.github.capitole.products.adapter.db.model.filters.products.HasStockProduct;
import io.github.capitole.products.adapter.db.model.filters.products.IsBackSoonProduct;
import io.github.capitole.products.adapter.db.model.filters.products.IsSpecialProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private Integer sequence;
    @Builder.Default
    private List<Size> sizes = new ArrayList<>();

    public Product(String[] elements) {
        this.id = Long.parseLong(cleanString(elements[0]));
        this.sequence = Integer.parseInt(cleanString(elements[1]));
        this.sizes = new ArrayList<>();
    }

    public boolean canShow() {
        return new AnyCompositeFilter(
            new IsBackSoonProduct(this),
            new IsSpecialProduct(this),
            new HasStockProduct(this)
        ).isValid();
    }
}
