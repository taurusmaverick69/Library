package com.maverick.oldDAO;

import com.maverick.oldDAO.dbmsdaofactory.HibernateDAOFactory;
import com.maverick.oldDAO.dbmsdaofactory.MongoDBDAOFactory;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.*;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(TypeDAO typeDAO) {
        switch (typeDAO) {
            case MySQL:
                return new MySQLDAOFactory();
            case MongoDB:
                return new MongoDBDAOFactory();
            case Hibernate:
                return new HibernateDAOFactory();
        }
        return new MySQLDAOFactory();
    }

    public abstract AuthorDAO getAuthorDAO();

    public abstract BookDAO getBookDAO();

    public abstract GenreDAO getGenreDAO();

    public abstract GroupDAO getGroupDAO();

    public abstract LibrarianDAO getLibrarianDAO();

    public abstract OrderDAO getOrderDAO();

    public abstract PublisherDAO getPublisherDAO();

    public abstract StudentDAO getStudentDAO();

}
