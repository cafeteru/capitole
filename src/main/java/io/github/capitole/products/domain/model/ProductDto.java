package io.github.capitole.products.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Product")
public class ProductDto {
    @Schema(description = "Id of the product", example = "1")
    private Long id;
}
