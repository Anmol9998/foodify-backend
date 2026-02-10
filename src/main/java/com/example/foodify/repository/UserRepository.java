package com.example.foodify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodify.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
}