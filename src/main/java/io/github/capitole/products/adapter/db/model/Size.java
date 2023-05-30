package io.github.capitole.products.adapter.db.model;

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
}
