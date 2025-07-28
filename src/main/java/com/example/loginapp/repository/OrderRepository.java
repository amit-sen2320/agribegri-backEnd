package com.example.loginapp.repository;

import com.example.loginapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 🔹 Fetch all orders for a user
    List<Order> findByUserId(Long userId);

    // 🔹 Filter orders based on multiple optional parameter

    @Query("SELECT o FROM Order o WHERE o.user.id = :userId " +
            "AND (:status IS NULL OR o.status = :status) " +
            "AND (:paymentStatus IS NULL OR o.paymentStatus = :paymentStatus) " +
            "AND (:sellerPaymentStatus IS NULL OR o.sellerPaymentStatus = :sellerPaymentStatus) " +
            "AND (" +
            "  (:dateType = 'orderDate' AND o.orderDate BETWEEN :fromDate AND :toDate) OR " +
            "  (:dateType = 'paymentDate' AND o.paymentDate BETWEEN :fromDate AND :toDate)" +
            ")")
    List<Order> filterOrders(
            @Param("userId") Long userId,
            @Param("status") String status,
            @Param("paymentStatus") String paymentStatus,
            @Param("sellerPaymentStatus") String sellerPaymentStatus,
            @Param("dateType") String dateType,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );

}
