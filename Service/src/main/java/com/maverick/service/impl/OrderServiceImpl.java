package com.maverick.service.impl;

import com.maverick.domain.Order;
import com.maverick.repository.OrderRepository;
import com.maverick.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findOne(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.delete(id);
    }
}
