package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Author;
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
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.save(author);
        }
        return true;
    }

    @Override
    public boolean update(Author author) {
        return false;
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session
                    .createQuery("delete Author where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }
}
