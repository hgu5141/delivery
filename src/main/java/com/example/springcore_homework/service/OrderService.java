package com.example.springcore_homework.service;


import com.example.springcore_homework.dto.FoodsResponseDto;
import com.example.springcore_homework.dto.OrdersRequestDto;
import com.example.springcore_homework.dto.OrdersResponseDto;
import com.example.springcore_homework.model.Food;
import com.example.springcore_homework.model.OrderItem;
import com.example.springcore_homework.model.Orders;
import com.example.springcore_homework.model.Restaurant;
import com.example.springcore_homework.repository.FoodRepository;
import com.example.springcore_homework.repository.OrderItemRepository;
import com.example.springcore_homework.repository.OrderRepository;
import com.example.springcore_homework.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrdersResponseDto order(OrdersRequestDto ordersRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(ordersRequestDto.getRestaurantId())
                .orElseThrow(
                        () -> new NullPointerException("해당 음식점이 없습니다.")
                );
        int totalPrice = 0;
        List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
        List<OrderItem> orderItems = ordersRequestDto.getFoods();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItem tempOrderItem : orderItems) {

            int quantity = tempOrderItem.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("음식 주문 수량은 1 ~ 100미만으로 입니다.");
            }

            Food food = foodRepository.findById(tempOrderItem.getId())
                    .orElseThrow(() -> new NullPointerException("해당 음식이 없습니다."));

            OrderItem orderItem = OrderItem.builder()
                    .quantity(tempOrderItem.getQuantity())
                    .name(food.getName())
                    .price(food.getPrice() * quantity)
                    .food(food)
                    .build();
            orderItemRepository.save(orderItem);
            FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderItem);
            foodsResponseDtoList.add(foodsResponseDto);
            totalPrice += food.getPrice() * quantity;
            orderItemList.add(orderItem);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("음식점의 최소 주문 가격을 넘지 않았습니다.");
        }

        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;
        Orders orders = new Orders(restaurant.getName(), totalPrice, orderItemList);
        orderRepository.save(orders);
        return new OrdersResponseDto(orders, deliveryFee, foodsResponseDtoList);
    }

    @Transactional
    public List<OrdersResponseDto> findAllOrder() {
        List<OrdersResponseDto> ordersResponseDtoList = new ArrayList<>();
        List<Orders> ordersList = orderRepository.findAll();

        for (Orders orders : ordersList) {
            System.out.println("start_target");
            int deliveryFee = restaurantRepository.findByName(orders.getRestaurantName()).getDeliveryFee();
            List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
            List<OrderItem> orderItemsList  = orderItemRepository.findAll();
            System.out.println(orderItemsList.size());

            for (OrderItem orderItem : orderItemsList) {
                System.out.println("target");
                FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderItem);
                System.out.println(foodsResponseDto);
                foodsResponseDtoList.add(foodsResponseDto);
            }
            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, deliveryFee, foodsResponseDtoList);
            ordersResponseDtoList.add(ordersResponseDto);
        }

        return ordersResponseDtoList;
    }
}
