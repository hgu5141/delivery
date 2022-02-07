package com.example.springcore_homework.controller;

import com.example.springcore_homework.dto.FoodRequestDto;
import com.example.springcore_homework.model.Food;
import com.example.springcore_homework.repository.FoodRepository;
import com.example.springcore_homework.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodRepository foodRepository;
    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
        public Food addFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> requestDtoList){
        return foodService.addFood(requestDtoList, restaurantId);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> readFood(@PathVariable Long restaurantId){
        return foodService.readFood(restaurantId);
    }

}
