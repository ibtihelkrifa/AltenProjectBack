package com.altran.product_trial.infrastructure.mapper;

import com.altran.product_trial.domain.model.InventoryStatus;
import com.altran.product_trial.domain.model.Product;
import com.altran.product_trial.infrastructure.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {

    public Product toDomain(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCode(productDTO.getCode());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setCategory(productDTO.getCategory());
        product.setInternalReference(productDTO.getInternalReference());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setShellId(productDTO.getShellId());
        product.setRating(productDTO.getRating());
        if (productDTO.getInventoryStatus() != null) {
            product.setInventoryStatus(InventoryStatus.valueOf(productDTO.getInventoryStatus()));
        }
        return product;
    }

    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCode(product.getCode());
        dto.setDescription(product.getDescription());
        dto.setImage(product.getImage());
        dto.setCategory(product.getCategory());
        dto.setInternalReference(product.getInternalReference());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setShellId(product.getShellId());
        dto.setRating(product.getRating());
        dto.setInventoryStatus(product.getInventoryStatus() != null
                ? product.getInventoryStatus().name()
                : null);

        return dto;
    }
}
