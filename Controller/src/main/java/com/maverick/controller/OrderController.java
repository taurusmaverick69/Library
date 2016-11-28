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

    @RequestMapping
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @RequestMapping("/{id}")
    public Order findById(@PathVariable("id") int id) {
        return orderService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@ModelAttribute("book") Order order) {
        orderService.save(order);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        orderService.delete(id);
    }

}
