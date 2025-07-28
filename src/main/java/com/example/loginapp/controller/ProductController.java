

package com.example.loginapp.controller;

import com.example.loginapp.model.Product;
import com.example.loginapp.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")

public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ✅ 1. Get all products for a user
    @GetMapping("/user/{userId}")
    public List<Product> getProductsByUser(@PathVariable Long userId) {
        return productRepository.findByUserId(userId);
    }

    // ✅ 2. Get filtered products
    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam Long userId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String stock,
            @RequestParam(required = false) String keyword) {

        return productRepository.filterProducts(
                userId,
                status,
                stock,
                keyword != null ? keyword.toLowerCase() : ""
        );
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // ✅ Get all products publicly visible (e.g., status = Active)
    @GetMapping("/public")
    public List<Product> getAllPublicProducts() {
        return productRepository.findByStatus("Active");
    }


}
