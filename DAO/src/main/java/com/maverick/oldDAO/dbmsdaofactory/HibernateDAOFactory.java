package com.maverick.oldDAO.dbmsdaofactory;

import com.maverick.oldDAO.DAOFactory;
import com.maverick.oldDAO.dbmsentitydao.hibernate.*;
import com.maverick.oldDAO.entitydao.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateDAOFactory extends DAOFactory {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();
            Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
            sessionFactory = metadata.getSessionFactoryBuilder().build();
        }
        return sessionFactory;
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