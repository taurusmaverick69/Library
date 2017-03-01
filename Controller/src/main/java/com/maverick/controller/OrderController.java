package com.maverick.controller;

import com.maverick.domain.Order;
import com.maverick.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") int id) {
        return orderService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Order order) {
        orderService.save(order);
    }

    @DeleteMapping
    public void delete(@PathVariable("id") int id) {
        orderService.delete(id);
    }
}