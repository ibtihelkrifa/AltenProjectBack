package com.altran.product_trial.infrastructure.jpa;

import com.altran.product_trial.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends JpaRepository<ProductEntity, Integer> {

}
