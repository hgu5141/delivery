package com.example.springcore_homework.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Food food;

    @ManyToOne
    @JoinColumn(name="orders_is")
    private Orders orders;

    @Builder
    public OrderItem(int quantity, String name, int price, Food food) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.food = food;
//        this.orders = orders;
    }
}
