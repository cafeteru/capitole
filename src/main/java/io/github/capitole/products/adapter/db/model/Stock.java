package io.github.capitole.products.adapter.db.model;

import static io.github.capitole.products.adapter.db.util.CsvUtils.cleanString;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Stock {
    private Long sizeId;
    private Long quantity;

    public Stock(String[] elements) {
        this.sizeId = Long.parseLong(cleanString(elements[0]));
        this.quantity = Long.parseLong(cleanString(elements[1]));
    }
}
