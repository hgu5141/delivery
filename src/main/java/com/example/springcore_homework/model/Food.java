package com.example.springcore_homework.model;

import com.example.springcore_homework.dto.FoodRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "Restaurant_Id", nullable = false)
    private Restaurant restaurant;



//    @Builder
//    public Food(Long id, String name, int price, Restaurant restaurant) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.restaurant = restaurant;
//    }


    @Builder
    public Food(
            FoodRequestDto requestDto,
            Restaurant restaurant
    ) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurant = restaurant;
    }








}
