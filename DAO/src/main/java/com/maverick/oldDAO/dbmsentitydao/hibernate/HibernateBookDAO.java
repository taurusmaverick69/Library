package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Book;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.BookDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class HibernateBookDAO implements BookDAO {
    @Override
    public boolean save(Book book) {

        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.save(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public boolean delete(Book book) {
        Session session = HibernateDAOFactory.openSession();
        try {

            session.beginTransaction();
//            try {
//                session.createQuery("delete Book where id = :ID").setParameter("ID", book.getId()).executeUpdate();
//            } catch (ConstraintViolationException constraintViolationException) {
//                return false;
//            }
            session.delete(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    @Override
    public List<Book> findAll() {
        Session session = HibernateDAOFactory.openSession();
        List<Book> books = new ArrayList<>();
        try {
            session.beginTransaction();
            books.addAll(session.createCriteria(Book.class).list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return books;
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public boolean update(Book book) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.update(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }

        return true;
    }

    @Override
    public int selectAmount(Book book) {
        Session session = HibernateDAOFactory.openSession();
        List amount = new ArrayList<>();
        try {
            session.beginTransaction();
            amount = session.createCriteria(Book.class).setProjection(Projections.property("amount")).add(Restrictions.eq("title", book.getTitle())).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return (int) amount.get(0);
    }


//    @Override
//    public boolean checkPassword(Librarian librarian) {
//        Session session = null;
//        String password = "";
//        try {
//            session = getSessionFactory().openSession();
//            session.beginTransaction();
//            password = (String) session.createCriteria(Librarian.class)
//                    .setProjection(Projections.property("password"))
//                    .add(Restrictions.eq("fullName", librarian.getFullName()))
//                    .list().get(0);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (session != null && session.isOpen()) {
//                try {
//                    session.close();
//                } catch (HibernateException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return password.equals(librarian.getPassword());
//    }
}
