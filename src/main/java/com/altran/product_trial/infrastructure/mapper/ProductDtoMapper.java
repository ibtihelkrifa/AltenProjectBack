package com.altran.product_trial.infrastructure.mapper;

import com.altran.product_trial.infrastructure.dto.ProductDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public com.altran.product_trial.domain.model.Product toDomain(ProductDTO productDTO) {
        com.altran.product_trial.domain.model.Product product = new com.altran.product_trial.domain.model.Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }

    public ProductDTO toDTO(com.altran.product_trial.domain.model.Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }
}
