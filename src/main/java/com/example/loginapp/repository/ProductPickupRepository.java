// ProductPickupRepository.java
package com.example.loginapp.repository;

import com.example.loginapp.model.ProductPickup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPickupRepository extends JpaRepository<ProductPickup, Long> {
    ProductPickup findByUserId(Long userId);
}

