package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Order;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.OrderDAO;
import org.hibernate.Session;

import java.util.List;

public class HibernateOrderDAO implements OrderDAO {

    @Override
    public List<Order> findAll() {
        System.err.println("HibernateOrderDAO.findAll");
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Order ", Order.class).list();
        }
    }

    @Override
    public List<Order> findByLibrarianId(int librarianId) {
        return null;
    }

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

    @Override
    public boolean delete(int id) {
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
}
