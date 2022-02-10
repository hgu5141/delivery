package com.example.springcore_homework.service;

import com.example.springcore_homework.dto.FoodRequestDto;
import com.example.springcore_homework.model.Food;
import com.example.springcore_homework.model.Restaurant;
import com.example.springcore_homework.repository.FoodRepository;
import com.example.springcore_homework.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public void addFood(List<FoodRequestDto> requestDtoList, Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);


        for(FoodRequestDto requestDto : requestDtoList) {
            if (!(requestDto.getPrice() >= 100 && requestDto.getPrice() <= 1000000)) {
                throw new IllegalArgumentException("1,000원부터 1,000,000원 까지 입력 가능합니다.");
            }

            if (requestDto.getPrice() % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로 입력 가능합니다.");
            }

            Optional<Food> found = foodRepository.findFoodsByRestaurantAndName(restaurant, requestDto.getName());
            if(found.isPresent()){
                throw new IllegalArgumentException("이미 존재하는 메뉴입니다.");
            }
            Food food = new Food(requestDto, restaurant);
            foodRepository.save(food);

        }
    }

    @Transactional
    public List<Food> readFood(Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 레스토랑이 없습니다."));
        return foodRepository.findFoodsByRestaurant(restaurant);
    }
}
