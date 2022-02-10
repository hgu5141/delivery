package com.example.springcore_homework.dto;

import com.example.springcore_homework.model.OrderItem;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FoodsResponseDto {
    private String name;
    private int quantity;
    private int price;

    @Builder
    public FoodsResponseDto(OrderItem orderItem) {
        this.name = orderItem.getName();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
    }
}
