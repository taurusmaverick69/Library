package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Author;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import org.hibernate.Session;

import java.util.List;

public class HibernateAuthorDAO implements AuthorDAO {

    @Override
    public List<Author> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Author ", Author.class).list();
        }
    }

    @Override
    public Author findById(int id) {
        return null;
    }

    @Override
    public boolean save(Author author) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.save(author);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }

    @Override
    public boolean update(Author author) {
        return false;
    }

    @Override
    public boolean delete(Author author) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.delete(author);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //    HibernateDAOFactory.closeSession();
//        }
        return true;
    }
}
