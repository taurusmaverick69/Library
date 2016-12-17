package com.maverick.service;

import com.maverick.domain.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(int id);

    void save(Order order);

    void delete(int id);
}
