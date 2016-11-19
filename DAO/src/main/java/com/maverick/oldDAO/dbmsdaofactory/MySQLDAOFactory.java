package com.maverick.oldDAO.dbmsdaofactory;

import com.maverick.oldDAO.DAOFactory;
import com.maverick.oldDAO.dbmsentitydao.mysql.*;
import com.maverick.oldDAO.entitydao.*;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/booksdb?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection createConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                DriverManager.registerDriver(new FabricMySQLDriver());
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public AuthorDAO getAuthorDAO() {
        return new MySQLAuthorDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new MySQLBookDAO();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return new MySQLGenreDAO();
    }

    @Override
    public GroupDAO getGroupDAO() {
        return new MySQLGroupDAO();
    }

    @Override
    public LibrarianDAO getLibrarianDAO() {
        return new MySQLLibrarianDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MySQLOrderDAO();
    }

    @Override
    public PublisherDAO getPublisherDAO() {
        return new MySQLPublisherDAO();
    }

    @Override
    public StudentDAO getStudentDAO() {
        return new MySQLStudentDAO();
    }
}
