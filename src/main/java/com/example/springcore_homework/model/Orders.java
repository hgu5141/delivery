package com.example.springcore_homework.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private int totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> foods;

    @Column(nullable = false)
    private String restaurantName;

    @Builder
    public Orders(String restaurantName, int totalPrice, List<OrderItem> orderItems) {
        this.restaurantName = restaurantName;
        this.totalPrice = totalPrice;
        this.foods = orderItems;
    }
}
