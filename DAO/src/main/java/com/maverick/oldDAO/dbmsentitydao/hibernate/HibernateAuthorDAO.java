package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Author;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateAuthorDAO implements AuthorDAO {

    @Override
    public boolean insertAuthor(Author author) {
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
    public boolean deleteAuthor(Author author) {
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

    @Override
    public List<Author> selectAuthors() {

        Session session = HibernateDAOFactory.openSession();
        List<Author> authors = new ArrayList<>();
        try {
            session.beginTransaction();
            authors.addAll(session.createCriteria(Author.class).list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return authors;
    }

    @Override
    public boolean updateAuthor(Author author) {
        return false;
    }
}
