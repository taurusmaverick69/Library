package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Order;
import com.maverick.oldDAO.entitydao.OrderDAO;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import org.hibernate.Session;

import java.util.List;

public class HibernateOrderDAO implements OrderDAO {
    @Override
    public boolean insertOrder(Order order) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }


    @Override
    public boolean deleteOrder(Order order) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public List<Order> selectOrders() {
        return null;
    }

    @Override
    public boolean updateOrder(Order order) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public List<Order> searchOrder(Order order) {
        return null;
    }
}
