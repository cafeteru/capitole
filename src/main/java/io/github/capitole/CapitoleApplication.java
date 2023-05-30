package io.github.capitole;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.capitole.products.adapter.db.impl.ProductRepositoryImpl;

@SpringBootApplication
public class CapitoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapitoleApplication.class, args);
    }

    @PostConstruct
    public void init() {
        var a = new ProductRepositoryImpl();
        a.findAll();
    }
}
