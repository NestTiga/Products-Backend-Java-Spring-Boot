package com.tigasinestor.products.controllers;

import com.tigasinestor.products.errors.PresentException;
import com.tigasinestor.products.model.entities.Product;
import com.tigasinestor.products.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController("product_controller_v1")
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/findAllDesired")
    public ResponseEntity<List<Product>> getAllProductsDesired() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductsDesired());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id));
    }

    @PutMapping("/desiredProduct/{id}")
    public ResponseEntity<Product> changeDesiredProduct(@Valid @RequestBody Product product, @PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.changeDesiredProduct(product, id));
    }

    @PutMapping("/activeProduct/{id}")
    public ResponseEntity<Boolean> changeActiveProduct(@Valid @RequestBody Product product, @PathVariable Long id) throws PresentException {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.deleteProductById(product, id));
    }
}
