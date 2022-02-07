package com.example.springcore_homework.controller;

import com.example.springcore_homework.dto.RestaurantRequestDto;
import com.example.springcore_homework.model.Restaurant;
import com.example.springcore_homework.repository.RestaurantRepository;

import com.example.springcore_homework.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;


    @GetMapping("/restaurants")
    public List<Restaurant> readRestaurant(String name){
        return restaurantService.findAllRestaurant();
    }

    @PostMapping("/restaurant/register")
    public Restaurant addRestaurant(@RequestBody RestaurantRequestDto requestDto){
    return restaurantService.addRestaurant(requestDto);
    }





}
