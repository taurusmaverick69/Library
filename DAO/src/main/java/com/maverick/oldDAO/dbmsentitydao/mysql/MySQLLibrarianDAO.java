package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Librarian;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.LibrarianDAO;
import com.maverick.oldDAO.entitydao.OrderDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLLibrarianDAO implements LibrarianDAO {

    private final static String LIBRARIAN_ID = "librarian.id";
    private final static String LIBRARIAN_FULL_NAME = "librarian.full_name";
    private final static String LIBRARIAN_PASSWORD = "librarian.password";

    private final static String FIND_ALL = "SELECT * FROM librarian";
    private final static String FIND_BY_ID = "SELECT * FROM librarian WHERE id = ?";
    private final static String INSERT_LIBRARIAN = "INSERT INTO librarian VALUES (DEFAULT, ?, md5(?))";

    private OrderDAO orderDAO;

    @Override
    public List<Librarian> findAll() {

        orderDAO = new MySQLOrderDAO();

        List<Librarian> librarians = new ArrayList<>();
        try (Statement statement = MySQLDAOFactory.createConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            while (resultSet.next()) {
                Librarian librarian = new Librarian();
                int librarianId = resultSet.getInt(LIBRARIAN_ID);
                librarian.setId(librarianId);
                librarian.setFullName(resultSet.getString(LIBRARIAN_FULL_NAME));
                librarian.setPassword(resultSet.getString(LIBRARIAN_PASSWORD));
                librarian.setOrders(orderDAO.findByLibrarianId(librarianId));
                librarians.add(librarian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarians;
    }

    @Override
    public Librarian findById(int id) {

        orderDAO = new MySQLOrderDAO();

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Librarian librarian = new Librarian();
                int librarianId = resultSet.getInt(LIBRARIAN_ID);
                librarian.setId(librarianId);
                librarian.setFullName(resultSet.getString(LIBRARIAN_FULL_NAME));
                librarian.setPassword(resultSet.getString(LIBRARIAN_PASSWORD));
                librarian.setOrders(orderDAO.findByLibrarianId(librarianId));
                return librarian;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Librarian librarian) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIBRARIAN)) {
            preparedStatement.setString(1, librarian.getFullName());
            preparedStatement.setString(2, librarian.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Librarian librarian) {
        return false;
    }

    @Override
    public boolean update(Librarian librarian) {
        return false;
    }
}
