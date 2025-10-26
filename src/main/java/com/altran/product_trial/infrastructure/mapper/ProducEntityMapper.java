package com.altran.product_trial.infrastructure.mapper;

import com.altran.product_trial.domain.model.Product;
import com.altran.product_trial.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProducEntityMapper {


    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setCode(entity.getCode());
        product.setDescription(entity.getDescription());
        product.setImage(entity.getImage());
        product.setCategory(entity.getCategory());
        product.setInternalReference(entity.getInternalReference());
        product.setPrice(entity.getPrice());
        product.setQuantity(entity.getQuantity());
        product.setShellId(entity.getShellId());
        product.setRating(entity.getRating());
        product.setInventoryStatus(entity.getInventoryStatus());

        return product;
    }

    public ProductEntity toEntity(Product product) {
        if (product == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setCode(product.getCode());
        entity.setDescription(product.getDescription());
        entity.setImage(product.getImage());
        entity.setCategory(product.getCategory());
        entity.setInternalReference(product.getInternalReference());
        entity.setPrice(product.getPrice());
        entity.setQuantity(product.getQuantity());
        entity.setShellId(product.getShellId());
        entity.setRating(product.getRating());
        entity.setInventoryStatus(product.getInventoryStatus());

        return entity;
    }
}
