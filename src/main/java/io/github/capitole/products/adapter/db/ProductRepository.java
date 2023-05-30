package io.github.capitole.products.adapter.db;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import io.github.capitole.products.adapter.db.model.Product;

public class ProductRepository {

    String csvFile = "csv/product.csv";

    public void readCSV(String csvFile) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                Long aLong = Long.parseLong(cleanString(line[0]));
                int sequence = Integer.parseInt(cleanString(line[1]));
                var product = Product.builder()
                    .id(aLong)
                    .sequence(sequence)
                    .build();
                System.out.println(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String cleanString(String string) {
        return string.replaceAll("\\s+", "");
    }
}
