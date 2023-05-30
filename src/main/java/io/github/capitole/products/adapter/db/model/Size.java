package io.github.capitole.products.adapter.db.model;

import static io.github.capitole.products.adapter.db.util.CsvUtils.cleanString;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    private Long id;
    private Long productId;
    private boolean backSoon;
    private boolean special;
    private Stock stock;

    public Size(String[] elements) {
        this.id = Long.parseLong(cleanString(elements[0]));
        this.productId = Long.parseLong(cleanString(elements[1]));
        this.backSoon = Boolean.parseBoolean(cleanString(elements[2]));
        this.special = Boolean.parseBoolean(cleanString(elements[3]));
    }
}
