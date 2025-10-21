package com.altran.product_trial.application.port.in;

import com.altran.product_trial.dto.ProductDTO;

import java.util.List;

public interface ProductServiceInterface {
    List<ProductDTO> getAllProducts();

    Integer createProduct(ProductDTO product);

    ProductDTO getProductById(Integer productId);
}
