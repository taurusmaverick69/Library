package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.oldDAO.entitydao.LibrarianDAO;
import com.maverick.domain.Librarian;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateLibrarianDAO implements LibrarianDAO {
    @Override
    public boolean insertLibrarian(Librarian librarian) {
        Session session = HibernateDAOFactory.openSession();
        try {
            session.beginTransaction();
            session.save(librarian);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateDAOFactory.closeSession();
        }
        return true;
    }

    @Override
    public boolean deleteLibrarian(Librarian librarian) {
        return false;
    }

    @Override
    public List<Librarian> selectLibrarians() {
        Session session = HibernateDAOFactory.openSession();
        List<Librarian> librarians = new ArrayList<>();
        try {
            session.beginTransaction();
            librarians.addAll(session.createCriteria(Librarian.class).list());
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
        return librarians;
    }

    @Override
    public boolean updateLibrarian(Librarian librarian) {
        return false;
    }

    @Override
    public boolean checkPassword(Librarian librarian) {
        return false;
    }
}
