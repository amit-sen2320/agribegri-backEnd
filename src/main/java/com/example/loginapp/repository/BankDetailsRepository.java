// BankDetailsRepository.java
package com.example.loginapp.repository;

import com.example.loginapp.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Long> {
    BankDetails findByUserId(Long userId);
}