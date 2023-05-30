package io.github.capitole.products.adapter.db.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.opencsv.CSVReader;

public class CsvUtils {

    private CsvUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String[]> readCSV(String csvFile) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> lines = new ArrayList<>();
            String[] line;
            while (Objects.nonNull((line = reader.readNext()))) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static String cleanString(String string) {
        return string.replaceAll("\\s+", "");
    }
}
