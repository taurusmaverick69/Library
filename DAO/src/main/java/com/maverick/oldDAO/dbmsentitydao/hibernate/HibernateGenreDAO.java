package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Genre;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.GenreDAO;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateGenreDAO implements GenreDAO {
    @Override
    public boolean insertGenre(Genre genre) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.save(genre);
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
    public boolean deleteGenre(Genre genre) {
        return false;
    }

    @Override
    public List<Genre> selectGenres() {
        Session session = HibernateDAOFactory.openSession();
        List<Genre> genres = new ArrayList<>();
        try {
            session.beginTransaction();
            genres.addAll(session.createCriteria(Genre.class).list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return genres;
    }

    @Override
    public boolean updateGenre(Genre genre) {
        return false;
    }
}
