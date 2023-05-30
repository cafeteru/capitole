package io.github.capitole.products.adapter.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/products")
public interface ProductAdapter {

    @Operation(summary = "REST endpoint for querying all products")
    @ApiResponse(responseCode = "200", description = "All filtered products' id sorted by sequence desc")
    @ApiResponse(responseCode = "204", description = "No results")
    @GetMapping
    ResponseEntity<List<Long>> findAll();
}

