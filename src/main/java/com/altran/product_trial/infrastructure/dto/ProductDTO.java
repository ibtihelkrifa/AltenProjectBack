package com.altran.product_trial.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Integer id;
    @NotBlank(message = "Le nom du produit est obligatoire")
    private String name;
    private String code;
    private String description;
    private String image;
    private String category;
    private String internalReference;
    @Min(value = 0, message = "Le prix doit être positif ou nul")
    private Double price = 0.0;
    private Integer quantity;
    private Integer shellId;
    private Integer rating;
    private String inventoryStatus;
}
