package com.altran.product_trial.domain.model;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private String code;
    private String description;
    private String image;
    private String category;
    private String internalReference;
    private Double price = 0.0;
    private Integer quantity;
    private Integer shellId;
    private Integer rating;
    private InventoryStatus inventoryStatus;
}
