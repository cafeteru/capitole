package io.github.capitole.products.adapter.db.impl;

import static io.github.capitole.products.adapter.db.util.CsvUtils.readCSV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.github.capitole.products.adapter.db.ProductRepository;
import io.github.capitole.products.adapter.db.model.Product;
import io.github.capitole.products.adapter.db.model.Size;
import io.github.capitole.products.adapter.db.model.Stock;

public class ProductRepositoryImpl implements ProductRepository {
    public List<Product> findAll() {
        var stocks = getStocks();
        var products = getProducts();
        var result = getSizes(stocks, products);
        return new ArrayList<>(result.values());
    }

    private HashMap<Long, Stock> getStocks() {
        HashMap<Long, Stock> stockHashMap = new HashMap<>();
        var lines = readCSV("csv/stock.csv");
        recursiveStockCreation(lines, stockHashMap, 0);
        return stockHashMap;
    }

    private void recursiveStockCreation(List<String[]> lines, HashMap<Long, Stock> stockHashMap, int index) {
        if (index >= lines.size()) {
            return;
        }

        var line = lines.get(index);
        var stock = new Stock(line);
        stockHashMap.put(stock.getSizeId(), stock);

        recursiveStockCreation(lines, stockHashMap, index + 1);
    }

    private HashMap<Long, Product> getProducts() {
        HashMap<Long, Product> productHashMap = new HashMap<>();
        var lines = readCSV("csv/product.csv");
        recursiveProductCreation(lines, productHashMap, 0);
        return productHashMap;
    }

    private void recursiveProductCreation(List<String[]> lines, HashMap<Long, Product> productHashMap, int index) {
        if (index >= lines.size()) {
            return;
        }
        var line = lines.get(index);
        var product = new Product(line);
        productHashMap.put(product.getId(), product);

        recursiveProductCreation(lines, productHashMap, index + 1);
    }

    private HashMap<Long, Product> getSizes(HashMap<Long, Stock> stockHashMap, HashMap<Long, Product> productHashMap) {
        var lines = readCSV("csv/size.csv");
        recursiveSizeCreation(lines, stockHashMap, productHashMap, 0);
        return productHashMap;
    }

    private void recursiveSizeCreation(
        List<String[]> lines, HashMap<Long, Stock> stockHashMap, HashMap<Long, Product> productHashMap, int index) {
        if (index >= lines.size()) {
            return;
        }

        var line = lines.get(index);
        var size = new Size(line);
        size.setStock(stockHashMap.get(size.getId()));
        var product = productHashMap.get(size.getProductId());
        product.getSizes().add(size);

        recursiveSizeCreation(lines, stockHashMap, productHashMap, index + 1);
    }
}
