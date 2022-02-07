package com.example.springcore_homework.service;

import com.example.springcore_homework.dto.RestaurantRequestDto;
import com.example.springcore_homework.model.Restaurant;
import com.example.springcore_homework.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant addRestaurant(RestaurantRequestDto requestDto) {

        if(!(requestDto.getMinOrderPrice() <= 100000 && requestDto.getMinOrderPrice() >= 1000)){
            throw new IllegalArgumentException("최소주문 가격 허용값을 벗어났습니다.");
        }

        if(requestDto.getMinOrderPrice()%100 != 0){
            throw new IllegalArgumentException("100원 단위로 입력 가능합니,다.");
        }

        if(0 > requestDto.getDeliveryFee() || requestDto.getDeliveryFee() > 10_000){
            System.out.println(requestDto.getDeliveryFee());
            throw new IllegalArgumentException("기본 배달비를 벗어났습니다.");
        }

        if(requestDto.getDeliveryFee()%500 != 0){
            throw new IllegalArgumentException("배달비는 500원 단위로 입력 가능합니다.");
        }

        Restaurant restaurant = Restaurant
                .builder()
                .name(requestDto.getName())
                .minOrderPrice(requestDto.getMinOrderPrice())
                .deliveryFee(requestDto.getDeliveryFee())
                .build();

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    @Transactional
    public List<Restaurant> findAllRestaurant() {
        return restaurantRepository.findAll();
    }
}
