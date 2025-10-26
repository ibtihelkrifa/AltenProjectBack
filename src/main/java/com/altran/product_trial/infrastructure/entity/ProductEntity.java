package com.altran.product_trial.infrastructure.entity;


import com.altran.product_trial.domain.model.InventoryStatus;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "product")
public class ProductEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
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
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;

}
