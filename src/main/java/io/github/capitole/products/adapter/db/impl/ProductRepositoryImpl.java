package io.github.capitole.products.adapter.db.impl;

import static io.github.capitole.products.adapter.db.util.CsvUtils.readCSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import io.github.capitole.products.adapter.db.ProductRepository;
import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;
import io.github.capitole.products.adapter.db.model.Stock;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    public List<Product> findAll() {
        final var stocks = getStocks();
        final var products = getProducts();
        final var results = getSizes(stocks, products);
        final var productArrayList = new ArrayList<>(results.values());
        return productArrayList.stream()
            .filter(Product::canShow)
            .collect(Collectors.toList());
    }

    private HashMap<Long, Stock> getStocks() {
        final HashMap<Long, Stock> stockHashMap = new HashMap<>();
        final var lines = readCSV("csv/stock.csv");
        recursiveStockCreation(lines, stockHashMap, 0);
        return stockHashMap;
    }

    private void recursiveStockCreation(
        final List<String[]> lines, final HashMap<Long, Stock> stockHashMap, final int index) {
        if (index >= lines.size()) {
            return;
        }

        final var line = lines.get(index);
        final var stock = new Stock(line);
        stockHashMap.put(stock.getSizeId(), stock);

        recursiveStockCreation(lines, stockHashMap, index + 1);
    }

    private HashMap<Long, Product> getProducts() {
        final HashMap<Long, Product> productHashMap = new HashMap<>();
        final var lines = readCSV("csv/product.csv");
        recursiveProductCreation(lines, productHashMap, 0);
        return productHashMap;
    }

    private void recursiveProductCreation(
        final List<String[]> lines, final HashMap<Long, Product> productHashMap, final int index) {
        if (index >= lines.size()) {
            return;
        }
        final var line = lines.get(index);
        final var product = new Product(line);
        productHashMap.put(product.getId(), product);

        recursiveProductCreation(lines, productHashMap, index + 1);
    }

    private HashMap<Long, Product> getSizes(
        final HashMap<Long, Stock> stockHashMap, final HashMap<Long, Product> productHashMap) {
        final var lines = readCSV("csv/size.csv");
        recursiveSizeCreation(lines, stockHashMap, productHashMap, 0);
        return productHashMap;
    }

    private void recursiveSizeCreation(
        final List<String[]> lines, final HashMap<Long, Stock> stockHashMap,
        final HashMap<Long, Product> productHashMap, final int index) {
        if (index >= lines.size()) {
            return;
        }

        final var line = lines.get(index);
        final var size = new Size(line);
        size.setStock(stockHashMap.get(size.getId()));
        final var product = productHashMap.get(size.getProductId());
        product.getSizes().add(size);

        recursiveSizeCreation(lines, stockHashMap, productHashMap, index + 1);
    }
}
