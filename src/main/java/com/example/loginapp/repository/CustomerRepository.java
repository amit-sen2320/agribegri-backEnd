package com.example.loginapp.repository;

import com.example.loginapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // To check login by phone and password
    Optional<Customer> findByPhoneAndPassword(String phone, String password);

    // Optional: to prevent duplicate phone numbers or emails during registration
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);
}
