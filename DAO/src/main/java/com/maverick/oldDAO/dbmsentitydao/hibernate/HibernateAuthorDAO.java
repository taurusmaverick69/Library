package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Author;
import com.maverick.domain.Book;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateAuthorDAO implements AuthorDAO {

    @Override
    public List<Author> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createNativeQuery("select * from author ", Author.class).list();
        }
    }

    @Override
    public Author findById(int id) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            Query<Author> query = session.createQuery("from Author where id = :id", Author.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
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
    public boolean delete(int id) {
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
