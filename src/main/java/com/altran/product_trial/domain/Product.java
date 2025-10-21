package com.altran.product_trial.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    private String description;
    private String image;
    private String category;
    private String internalReference;
    private Double price;
    private Integer quantity;
    private Integer shellId;
    private Integer rating;
    private InventoryStatus inventoryStatus;

}
