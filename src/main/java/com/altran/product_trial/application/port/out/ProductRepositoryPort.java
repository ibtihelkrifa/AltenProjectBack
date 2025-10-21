package com.altran.product_trial.application.port.out;

import com.altran.product_trial.domain.Product;

import java.util.List;

public interface ProductRepositoryPort {

    Product save(Product product);

    List<Product> findAll();
}
