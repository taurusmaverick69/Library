package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Librarian;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.LibrarianDAO;
import org.hibernate.Session;

import java.util.List;

public class HibernateLibrarianDAO implements LibrarianDAO {

    @Override
    public List<Librarian> findAllWithOrders() {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Librarian ", Librarian.class).list();
        }
    }

    @Override
    public Librarian findById(int id) {
        return null;
    }

    @Override
    public boolean save(Librarian librarian) {
        try (Session session = HibernateDAOFactory.getSessionFactory().openSession()) {
            session.save(librarian);
        }
        return true;
    }

    @Override
    public boolean update(Librarian librarian) {
        return false;
    }

    @Override
    public void delete(int id) {
    }
}
