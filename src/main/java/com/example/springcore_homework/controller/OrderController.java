package com.example.springcore_homework.controller;

import com.example.springcore_homework.dto.OrdersRequestDto;
import com.example.springcore_homework.dto.OrdersResponseDto;
import com.example.springcore_homework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/request")
    public OrdersResponseDto addOrder(@RequestBody OrdersRequestDto ordersRequestDto ){
        return orderService.order(ordersRequestDto);
    }

    @GetMapping("/orders")
    public List<OrdersResponseDto> readOrder(){
        return orderService.findAllOrder();
    }
}
