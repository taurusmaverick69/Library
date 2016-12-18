package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Order;
import com.maverick.oldDAO.entitydao.OrderDAO;

import java.util.List;

public class HibernateOrderDAO implements OrderDAO {
    @Override
    public boolean save(Order order) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.save(order);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }


    @Override
    public boolean delete(Order order) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.delete(order);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findByLibrarianId(int librarianId) {
        return null;
    }

    @Override
    public boolean update(Order order) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.update(order);
//            session.getTransaction().commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }
}
