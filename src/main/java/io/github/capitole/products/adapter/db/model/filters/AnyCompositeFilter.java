package io.github.capitole.products.adapter.db.model.filters;

import java.util.Arrays;

public class AnyCompositeFilter implements Filter {
    private final Filter[] filters;

    public AnyCompositeFilter(Filter... filters) {
        this.filters = filters;
    }

    @Override
    public boolean isValid() {
        return Arrays.stream(filters).parallel().anyMatch(Filter::isValid);
    }
}
