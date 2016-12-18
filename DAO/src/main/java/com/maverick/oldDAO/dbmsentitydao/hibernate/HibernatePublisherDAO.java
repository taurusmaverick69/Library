package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Publisher;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.PublisherDAO;
import org.hibernate.Session;

import java.util.List;

public class HibernatePublisherDAO implements PublisherDAO {

    @Override
    public List<Publisher> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Publisher ", Publisher.class).list();
        }
    }

    @Override
    public Publisher findById(int id) {
        return null;
    }

    @Override
    public boolean save(Publisher publisher) {

//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.save(publisher);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }

    @Override
    public boolean update(Publisher publisher) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
