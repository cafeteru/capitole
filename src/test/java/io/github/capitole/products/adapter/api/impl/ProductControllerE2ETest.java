package io.github.capitole.products.adapter.api.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductControllerE2ETest {

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:8081";
    }

    @Test
    void findAll_successfully() {
        String URL = "/products";
        Response response = RestAssured.given()
            .get(URL)
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response();
        List<Long> products = response.getBody().as(new TypeRef<>() {
        });
        assertFalse(products.isEmpty());
        assertEquals("[3, 4, 1, 2, 5]", products.toString());
    }
}