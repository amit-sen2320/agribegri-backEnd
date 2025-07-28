package com.example.loginapp.repository;

//package com.yourapp.repository;

import com.example.loginapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserId(Long userId); // used for fetching all products of a user

    List<Product> findByStatus(String status);


    @Query("SELECT p FROM Product p WHERE p.user.id = :userId " +
            "AND (:status IS NULL OR p.status = :status) " +
            "AND (:stock IS NULL OR " +
            "     (:stock = 'inStock' AND p.stock > 0) OR " +
            "     (:stock = 'outStock' AND p.stock = 0)) " +
            "AND (" +
            "  LOWER(p.name) LIKE %:keyword% OR " +
            "  LOWER(p.code) LIKE %:keyword% OR " +
            "  LOWER(p.company) LIKE %:keyword%" +
            ")")
    List<Product> filterProducts(@Param("userId") Long userId,
                                 @Param("status") String status,
                                 @Param("stock") String stock,
                                 @Param("keyword") String keyword);



}

