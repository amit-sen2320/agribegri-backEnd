package com.example.loginapp.repository;

import com.example.loginapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByEmailAndPassword(String email, String password);
    Optional<User> findByMobileAndPassword(String mobile, String password);
    Optional<User> findById(Long id);


    Optional<User> findByMobile(String mobile);


}

