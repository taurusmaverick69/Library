package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Book;
import com.maverick.domain.Order;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.BookDAO;
import com.maverick.oldDAO.entitydao.LibrarianDAO;
import com.maverick.oldDAO.entitydao.OrderDAO;
import com.maverick.oldDAO.entitydao.StudentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDAO implements OrderDAO {

    private final static String ORDER_ID = "order.id";
    private final static String ORDER_STUDENT_ID = "order.Student_id";
    private final static String ORDER_BOOK_ID = "order.Book_id";
    private final static String ORDER_LIBRARIAN_ID = "order.Librarian_id";
    private final static String ORDER_START_DATE = "order.start_date";
    private final static String ORDER_FINISH_DATE = "order.finish_date";
    private final static String ORDER_STATUS = "order.status";

    private final static String FIND_ORDERS_BY_LIBRARIAN_ID = "SELECT * FROM `order` WHERE Librarian_id = ?";
    private final static String INSERT_ORDER = "INSERT INTO `order` VALUES(DEFAULT, ?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d'), STR_TO_DATE(?, '%Y-%m-%d'), ?)";
    private final static String DELETE_ORDER = "DELETE FROM `order` WHERE id = ?";
    private final static String UPDATE_ORDER = "UPDATE `order` SET Student_id = ?, Book_id = ?, start_date = STR_TO_DATE(?, '%Y-%m-%d'), finish_date = STR_TO_DATE(?, '%Y-%m-%d'), status = ? WHERE id = ?";
    private final static String IS_ORDERED = "SELECT Book_id FROM `order`";

    private StudentDAO studentDAO;
    private BookDAO bookDAO;
    private LibrarianDAO librarianDAO;

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findByLibrarianId(int librarianId) {

        studentDAO = new MySQLStudentDAO();
        bookDAO = new MySQLBookDAO();
        librarianDAO = new MySQLLibrarianDAO();

        List<Order> orders = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_LIBRARIAN_ID)) {

            preparedStatement.setInt(1, librarianId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(ORDER_ID));
                order.setStudent(studentDAO.findById(resultSet.getInt(ORDER_STUDENT_ID)));
                order.setBook(bookDAO.findById(resultSet.getInt(ORDER_BOOK_ID)));
                order.setLibrarian(librarianDAO.findById(resultSet.getInt(ORDER_LIBRARIAN_ID)));
                order.setStartDate(resultSet.getDate(ORDER_START_DATE));
                order.setFinishDate(resultSet.getDate(ORDER_FINISH_DATE));
                order.setStatus(resultSet.getString(ORDER_STATUS));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Order order) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)) {

            preparedStatement.setInt(1, order.getStudent().getId());
            preparedStatement.setInt(2, order.getBook().getId());
            preparedStatement.setInt(3, order.getLibrarian().getId());
            preparedStatement.setDate(4, new Date(order.getStartDate().getTime()));
            preparedStatement.setDate(5, new Date(order.getFinishDate().getTime()));
            preparedStatement.setString(6, order.getStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Order order) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)) {
            preparedStatement.setInt(1, order.getStudent().getId());
            preparedStatement.setInt(2, order.getBook().getId());
            preparedStatement.setDate(3, new Date(order.getStartDate().getTime()));
            preparedStatement.setDate(4, new Date(order.getFinishDate().getTime()));
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setInt(6, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Order order) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)) {
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
