package com.altran.product_trial.infrastructure.controller;

import com.altran.product_trial.application.port.in.ProductServiceInterface;
import com.altran.product_trial.dto.ProductDTO;
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
    public ResponseEntity<Integer> createProduct(@RequestBody ProductDTO productDTO) {
        Integer productId = this.productServiceInterface.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @GetMapping("/")
    public List<ProductDTO> getAllProducts() {
        return this.productServiceInterface.getAllProducts();
    }

}
