package br.com.joaogodoi.cart.controllers;

import br.com.joaogodoi.cart.entities.Product;
import br.com.joaogodoi.cart.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
@Tag(name = "Product", description = "Endpoints for managing products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "List all products",
            description = "List all products",
            tags = {"Product"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping(value = "/{code}")
    @Operation(summary = "Get a product",
            description = "Get details of a product",
            tags = {"Product"},
            responses = {@ApiResponse(description = "Success", responseCode = "200",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    public ResponseEntity<Product> findByCode(@PathVariable Long code) {
        Product product = productService.findByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    @Operation(summary = "Create a new product",
            description = "Create a new product",
            tags = {"Product"},
            responses = {@ApiResponse(description = "Created", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productCreated = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @PutMapping(value = "/{code}")
    @Operation(summary = "Updates a product",
            description = "Updates a product",
            tags = {"Product"},
            responses = {@ApiResponse(description = "Updated", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)})
    public ResponseEntity<Product> updateProduct(@PathVariable Long code, @RequestBody Product product) {
        Product productCreated = productService.updateProduct(code, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }
}
