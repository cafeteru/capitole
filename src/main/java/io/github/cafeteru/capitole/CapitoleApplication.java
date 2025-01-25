package io.github.cafeteru.capitole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(namedQueriesLocation = "classpath:queries.properties")
public class CapitoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapitoleApplication.class, args);
    }

}
