package io.github.capitole.products.adapter.db.model;

import static io.github.capitole.products.adapter.db.util.CsvUtils.cleanString;

import java.util.ArrayList;
import java.util.List;

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
}
