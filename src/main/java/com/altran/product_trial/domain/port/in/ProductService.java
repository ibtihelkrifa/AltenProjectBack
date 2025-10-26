package com.altran.product_trial.domain.port.in;

import com.altran.product_trial.domain.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Integer createProduct(Product product);

    Product getProductById(Integer productId);

    Product updateProduct(Product product);

    void deleteProduct(Integer productId);
}
