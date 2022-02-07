package com.example.springcore_homework.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    @Builder
    public Restaurant(
            String name,
            int minOrderPrice,
            int deliveryFee
    ) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }


}
