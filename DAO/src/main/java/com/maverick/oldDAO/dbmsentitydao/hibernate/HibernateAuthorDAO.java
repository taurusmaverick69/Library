package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Author;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class HibernateAuthorDAO implements AuthorDAO {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("MY_EM").createEntityManager();

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> namedQuery = entityManager.createNamedQuery("Author.findAll", Author.class);
        return namedQuery.getResultList();
    }

    @Override
    public Author findById(int id) {
        return null;
    }

    @Override
    public boolean save(Author author) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.save(author);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public boolean update(Author author) {
        return false;
    }

    @Override
    public boolean delete(Author author) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(author);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }
}
