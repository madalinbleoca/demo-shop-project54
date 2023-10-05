package com.example.demoshopproject54.controller;

import com.example.demoshopproject54.exception.ResourceNotFoundException;
import com.example.demoshopproject54.model.Order;
import com.example.demoshopproject54.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders") //base URL
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("getOrderById/{id}")
    public ResponseEntity<Order> readOrderById(@PathVariable Long id) {
        Order orderFromDB = orderService.getOrderById(id).orElseThrow(() -> new ResourceNotFoundException("Order with id " + id +
                " doesn't exist in DB"));

        return new ResponseEntity<>(orderFromDB, HttpStatus.OK);
    }

    @GetMapping("getAllOrderByName/{name}")
    public ResponseEntity<List<Order>> readAllOrdersByName(@PathVariable String name) {
        List<Order> orderListFromDB = orderService.getAllOrdersByName(name);

        if (orderListFromDB.isEmpty()) {
            throw new ResourceNotFoundException("No orders with name " + name + " not found!");
        }

        return new ResponseEntity<>(orderListFromDB, HttpStatus.OK);
    }

    @PostMapping("/addNewOrder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order newOrder = orderService.saveOrder(order);

        return new ResponseEntity<>(newOrder, HttpStatus.OK);
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updateOrder = orderService.updateOrder(order);

        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrderById/{id}")
    public ResponseEntity<?> removeOrderByID(@PathVariable Long id) {
        Order orderFromDb = orderService.getOrderById(id).orElseThrow(() -> new ResourceNotFoundException("Order with id " + id
        + " doesn't exist in DB"));

        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Order with id " + id + " Was deleted!", HttpStatus.OK);
    }

}
