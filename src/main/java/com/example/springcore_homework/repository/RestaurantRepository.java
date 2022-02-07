package com.example.springcore_homework.repository;


import com.example.springcore_homework.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
    Restaurant findByName(String name);
}
