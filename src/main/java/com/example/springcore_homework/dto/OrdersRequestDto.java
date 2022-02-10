package com.example.springcore_homework.dto;

import com.example.springcore_homework.model.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {
    private Long restaurantId;
    private List<OrderItem> foods;
}