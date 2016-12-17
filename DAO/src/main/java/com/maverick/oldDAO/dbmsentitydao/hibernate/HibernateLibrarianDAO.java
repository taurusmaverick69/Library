package com.maverick.oldDAO.dbmsentitydao.hibernate;

import com.maverick.domain.Librarian;
import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.entitydao.LibrarianDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateLibrarianDAO implements LibrarianDAO {
    @Override
    public boolean save(Librarian librarian) {
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
    public boolean delete(Librarian librarian) {
        return false;
    }

    @Override
    public List<Librarian> findAll() {
        Session session = HibernateDAOFactory.openSession();


        Query query = session.createQuery("from Librarian");

//        List<Librarian> list = query.list();
//
//        list.forEach(System.out::println);

        return null;
    }

    @Override
    public Librarian findById(int id) {
        return null;
    }

    @Override
    public boolean update(Librarian librarian) {
        return false;
    }
}
