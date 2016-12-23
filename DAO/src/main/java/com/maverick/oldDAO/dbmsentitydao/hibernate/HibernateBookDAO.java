package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Book;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.BookDAO;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

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
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            Query<Book> query = session.createQuery("from Book where id = :id", Book.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }

    @Override
    public boolean save(Book book) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.save(book);
            return true;
        }
    }

    @Override
    public boolean update(Book book) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(book);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            int executeUpdate = session
                    .createQuery("delete Book where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public int selectAmount(Book book) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return (int) session.createCriteria(Book.class).setProjection(Projections.property("amount")).add(Restrictions.eq("title", book.getTitle())).uniqueResult();
        }
    }

    public long sumAmount() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("select sum (book.amount) from Book book", Long.class).uniqueResult();
        }
    }
}