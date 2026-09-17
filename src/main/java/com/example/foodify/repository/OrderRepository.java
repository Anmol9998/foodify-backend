package com.example.foodify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.foodify.model.Order;
import com.example.foodify.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {



    List<Order> findByUser(User user);

    Optional<Order> findByIdAndUser(Long id, User user);
}