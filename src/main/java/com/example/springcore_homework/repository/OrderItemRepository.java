package com.example.springcore_homework.repository;

import com.example.springcore_homework.model.OrderItem;
import com.example.springcore_homework.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findOrderItemsByOrders(Orders orders);
}
