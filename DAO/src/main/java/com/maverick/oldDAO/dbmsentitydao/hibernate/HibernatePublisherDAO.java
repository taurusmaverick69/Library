package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Publisher;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.PublisherDAO;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernatePublisherDAO implements PublisherDAO {
    @Override
    public boolean save(Publisher publisher) {

        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.save(publisher);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public boolean delete(Publisher publisher) {
        return false;
    }

    @Override
    public List<Publisher> findAll() {
        Session session = HibernateDAOFactory.openSession();
        List<Publisher> publishers = new ArrayList<>();
        try {
            session.beginTransaction();
            publishers.addAll(session.createCriteria(Publisher.class).list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return publishers;
    }

    @Override
    public Publisher findById(int id) {
        return null;
    }

    @Override
    public boolean update(Publisher publisher) {
        return false;
    }
}
