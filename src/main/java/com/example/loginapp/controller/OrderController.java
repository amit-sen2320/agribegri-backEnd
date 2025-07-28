package com.example.loginapp.controller;

import com.example.loginapp.model.Order;
import com.example.loginapp.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // ✅ 1. Get all orders by user ID
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // ✅ 2. Filter orders with multiple params
    @GetMapping("/filter")
    public List<Order> filterOrders(
            @RequestParam Long userId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String paymentStatus,
            @RequestParam(required = false) String sellerPaymentStatus,
            @RequestParam String dateType,
            @RequestParam String fromDate,
            @RequestParam String toDate
    ) {
        LocalDate from = LocalDate.parse(fromDate);
        LocalDate to = LocalDate.parse(toDate);

        return orderRepository.filterOrders(
                userId,
                status,
                paymentStatus,
                sellerPaymentStatus,
                dateType,
                from,
                to
        );
    }


    // ✅ 3. (Optional) Add order - useful for testing
    @PostMapping("/add")
    public Order addOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}

