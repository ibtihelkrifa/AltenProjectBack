package com.altran.product_trial.infrastructure.repository;

import com.altran.product_trial.domain.model.Product;
import com.altran.product_trial.domain.port.out.ProductRepositoryPort;
import com.altran.product_trial.infrastructure.entity.ProductEntity;
import com.altran.product_trial.infrastructure.jpa.ProductJPARepository;
import com.altran.product_trial.infrastructure.mapper.ProducEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    @Autowired
    private ProductJPARepository productJPARepository;

    @Autowired
    private ProducEntityMapper productEntityMapper;

    public Product save(Product product) {
        ProductEntity productEntitySaved = productJPARepository.save(productEntityMapper.toEntity(product));
        return productEntityMapper.toDomain(productEntitySaved);
    }

    public List<Product> findAll() {
        return productJPARepository.findAll().stream().map(p -> productEntityMapper.toDomain(p)).toList();
    }

    public Optional<Product> findProductById(Integer productId) {
        return productJPARepository.findById(productId).map(p -> productEntityMapper.toDomain(p));
    }

    @Override
    public void deleteProduct(Integer productId) {
        productJPARepository.deleteById(productId);
    }


}

