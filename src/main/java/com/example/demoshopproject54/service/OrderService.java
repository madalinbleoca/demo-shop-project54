package com.example.demoshopproject54.service;

import com.example.demoshopproject54.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    //Read all order from database -> get HTTP ENDPOINT
    List<Order> getAllOrders();

    //Read data for an order by id -> GET (by id)
    Optional<Order> getOrderById(Long id);

    //Create new order and save it to database;
    Order saveOrder(Order order);

    //Update an order by id
    Order updateOrder(Order order);

    //Delete an order by id
    void deleteOrderById(Long id);

    //Custom CRUD method logic
    List<Order> getAllOrdersByName(String name);
}
