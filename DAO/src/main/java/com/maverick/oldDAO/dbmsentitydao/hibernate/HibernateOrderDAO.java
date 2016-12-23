package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Order;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.OrderDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateOrderDAO implements OrderDAO {

    @Override
    public List<Order> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Order ", Order.class).list();
        }
    }

    @Override
    public List<Order> findByLibrarianId(int librarianId) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            Query<Order> query = session.createQuery("from Order where librarian.id = :librarianId", Order.class);
            query.setParameter("librarianId", librarianId);
            return query.list();
        }
    }

    @Override
    public boolean save(Order order) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
            return true;
        }
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
    public void delete(int id) {
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
    }
}
