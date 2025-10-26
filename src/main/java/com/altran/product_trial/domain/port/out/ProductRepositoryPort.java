package com.altran.product_trial.domain.port.out;


import com.altran.product_trial.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findProductById(Integer productId);

    void deleteProduct(Integer productId);
}
