package com.altran.product_trial.application.port.out;

import com.altran.product_trial.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findProductById(Integer productId);
}
