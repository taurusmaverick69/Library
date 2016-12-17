package com.maverick.oldDAO.dbmsdaofactory;

import com.maverick.oldDAO.DAOFactory;
import com.maverick.oldDAO.dbmsentitydao.hibernate.*;
import com.maverick.oldDAO.entitydao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateDAOFactory extends DAOFactory {

    private static Session session;

    public static Session openSession() {

        if (session == null || !session.isConnected()) {
            Configuration configure = new Configuration().configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configure.getProperties()).build();
            SessionFactory sf = configure.buildSessionFactory(sr);
            session = sf.openSession();
        }
        return session;
    }

    public static void closeSession() {
        if (session != null && session.isOpen())
            session.close();
    }

    @Override
    public AuthorDAO getAuthorDAO() {
        return new HibernateAuthorDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new HibernateBookDAO();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return new HibernateGenreDAO();
    }

    @Override
    public GroupDAO getGroupDAO() {
        return new HibernateGroupDAO();
    }

    @Override
    public LibrarianDAO getLibrarianDAO() {
        return new HibernateLibrarianDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new HibernateOrderDAO();
    }

    @Override
    public PublisherDAO getPublisherDAO() {
        return new HibernatePublisherDAO();
    }

    @Override
    public StudentDAO getStudentDAO() {
        return new HibernateStudentDAO();
    }
}
