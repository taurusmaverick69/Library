package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Genre;
import com.maverick.oldDAO.entitydao.GenreDAO;

import java.util.ArrayList;
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
    public boolean delete(Genre genre) {
        return false;
    }

    @Override
    public List<Genre> findAll() {
//        Session session = HibernateDAOFactory.openSession();
        List<Genre> genres = new ArrayList<>();
//        try {
//            session.beginTransaction();
//            genres.addAll(session.createCriteria(Genre.class).list());
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateDAOFactory.closeSession();
//        }
        return genres;
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
