package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Genre;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.GenreDAO;
import org.hibernate.Session;

import java.util.List;

public class HibernateGenreDAO implements GenreDAO {

    @Override
    public boolean save(Genre genre) {
//        Session session = HibernateDAOFactory.openSession();
//        try {
//            session.beginTransaction();
//            session.save(genre);
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
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Genre> findAll() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Genre ", Genre.class).list();
        }
    }

    @Override
    public Genre findById(int id) {
        return null;
    }

    @Override
    public boolean update(Genre genre) {
        return false;
    }
}
