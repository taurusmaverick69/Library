package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> findAll();

    List<Order> findByLibrarianId(int librarianId);

    boolean save(Order order);

    boolean update(Order order);

    boolean delete(int id);
}
