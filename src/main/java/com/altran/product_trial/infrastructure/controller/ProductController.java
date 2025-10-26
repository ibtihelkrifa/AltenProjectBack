package com.altran.product_trial.infrastructure.controller;

import com.altran.product_trial.domain.port.in.ProductService;
import com.altran.product_trial.infrastructure.dto.ProductDTO;
import com.altran.product_trial.infrastructure.mapper.ProductDtoMapper;
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
    ProductService productServiceInterface;

    @Autowired
    ProductDtoMapper productDtoMapper;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Integer productId = this.productServiceInterface.createProduct(productDtoMapper.toDomain(productDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return this.productServiceInterface.getAllProducts().stream().map(p -> productDtoMapper.toDTO(p)).toList();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable Integer productId) {
        return productDtoMapper.toDTO(this.productServiceInterface.getProductById(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        if (productDTO.getId() == null || !productId.equals(productDTO.getId())) {
            throw new IllegalArgumentException("L'ID du corps doit correspondre à celui de l'URL.");
        }
        com.altran.product_trial.domain.model.Product updatedProduct = this.productServiceInterface.updateProduct(productDtoMapper.toDomain(productDTO));
        return ResponseEntity.ok(productDtoMapper.toDTO(updatedProduct));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId) {
        this.productServiceInterface.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
