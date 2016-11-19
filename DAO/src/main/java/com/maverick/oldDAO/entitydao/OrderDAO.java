package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Order;

import java.util.List;

public interface OrderDAO {

    boolean insertOrder(Order order);

    boolean deleteOrder(Order order);

    List<Order> selectOrders();

    boolean updateOrder(Order order);

    List<Order> searchOrder(Order order);
}
