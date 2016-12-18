package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Book;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.BookDAO;
import org.hibernate.Session;

import java.util.List;

public class HibernateBookDAO implements BookDAO {

    @Override
    public List<Book> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Book ", Book.class).list();
        }
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public boolean save(Book book) {

        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.save(book);
        }

//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.save(book);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return true;
    }

    @Override
    public boolean update(Book book) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.update(book);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }

        return true;
    }

    @Override
    public boolean delete(Book book) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            int executeUpdate = session
                    .createQuery("delete Book where id = :id")
                    .setParameter("id", book.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            return executeUpdate > 0;
        }
    }

    @Override
    public int selectAmount(Book book) {
//        Session session = HibernateDAOFactory.openSession();
//        List amount = new ArrayList<>();
//        try {
//            session.beginTransaction();
//            amount = session.createCriteria(Book.class).setProjection(Projections.property("amount")).add(Restrictions.eq("title", book.getTitle())).list();
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
//        return (int) amount.get(0);

        return 0;
    }
}