package com.altran.product_trial.infrastructure.repository;

import com.altran.product_trial.application.port.out.ProductRepositoryPort;
import com.altran.product_trial.domain.Product;
import com.altran.product_trial.infrastructure.jpa.ProductJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    @Autowired
    private ProductJPARepository productJPARepository;

    public Product save(Product product) {
        return productJPARepository.save(product);
    }

    public List<Product> findAll() {
        return productJPARepository.findAll();
    }

    public Optional<Product> findProductById(Integer productId) {
        return productJPARepository.findById(productId);
    }


}
