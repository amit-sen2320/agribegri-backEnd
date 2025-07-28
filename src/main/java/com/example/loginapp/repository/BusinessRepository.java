package com.example.loginapp.repository;

import com.example.loginapp.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    Business findByUserId(Long userId);
}

