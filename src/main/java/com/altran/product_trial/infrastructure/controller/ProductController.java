package com.altran.product_trial.infrastructure.controller;

import com.altran.product_trial.application.port.in.ProductServiceInterface;
import com.altran.product_trial.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductServiceInterface productServiceInterface;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Integer productId = this.productServiceInterface.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return this.productServiceInterface.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable Integer productId) {
        return this.productServiceInterface.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        if (productDTO.getId() == null || !productId.equals(productDTO.getId())) {
            throw new IllegalArgumentException("L'ID du corps doit correspondre à celui de l'URL.");
        }
        ProductDTO updatedProduct = this.productServiceInterface.updateProduct(productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId) {
        this.productServiceInterface.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
