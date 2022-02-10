package com.example.springcore_homework.repository;

import com.example.springcore_homework.model.Food;
import com.example.springcore_homework.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByRestaurantName(String restaurantName);
}
