package com.altran.product_trial.application;

import com.altran.product_trial.application.port.in.ProductServiceInterface;
import com.altran.product_trial.application.port.out.ProductRepositoryPort;
import com.altran.product_trial.dto.ProductDTO;
import com.altran.product_trial.domain.ProductMapper;
import com.altran.product_trial.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductRepositoryPort productRepositoryPort;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepositoryPort.findAll();
        return products.stream().map(product -> productMapper.mapEntityToDTO(product)).toList();
    }

    public Integer createProduct(ProductDTO product) {

        if (product == null) {
            throw new IllegalArgumentException("le produit ne doit pas être null");
        }
        if(product.getName() == null || product.getName().isEmpty() || product.getName().isBlank()) {
            throw new IllegalArgumentException("le nom du produit doit être rempli");
        }
        if(product.getPrice() < 0) {
            throw new IllegalArgumentException("le prix du produit doit être supérieur ou égal à zero");
        }
        Product createdProduct = productRepositoryPort.save(productMapper.mapDTOToEntity(product));
        return createdProduct.getId();
    }

}
