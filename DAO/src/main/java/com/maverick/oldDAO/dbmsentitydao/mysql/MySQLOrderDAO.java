package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Order;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.OrderDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrderDAO implements OrderDAO {

    private final static String INSERT_ORDER = "INSERT INTO booksdb.`order` (Student_id, Book_id, Librarian_id, startDate, finishDate, status) VALUES(?, ?, ?, STR_TO_DATE(?, '%Y-%m-%d'), STR_TO_DATE(?, '%Y-%m-%d'), ?)";
    private final static String DELETE_ORDER = "DELETE FROM booksdb.`order` WHERE id = ?";
    private final static String UPDATE_ORDER = "UPDATE booksdb.`order` SET Student_id = ?, Book_id = ?, startDate = STR_TO_DATE(?, '%Y-%m-%d'), finishDate = STR_TO_DATE(?, '%Y-%m-%d'), status = ? WHERE id = ?";

    @Override
    public boolean insertOrder(Order order) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)) {

            preparedStatement.setInt(1, order.getStudent().getId());
            preparedStatement.setInt(2, order.getBook().getId());
            preparedStatement.setInt(3, order.getLibrarian().getId());
            preparedStatement.setDate(4, new Date(order.getStartDate().getTime()));
            preparedStatement.setDate(5, new Date(order.getFinishDate().getTime()));
            preparedStatement.setString(6, order.getStatus());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public boolean deleteOrder(Order order) {
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

    @Override
    public List<Order> selectOrders() {
        return null;
    }

    @Override
    public boolean updateOrder(Order order) {
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
    public List<Order> searchOrder(Order order) {

//        try (Connection connection = MySQLDAOFactory.createConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(
//                     "SELECT id, Student_id, Book_id, DATE_FORMAT(startDate, '%d.%m.%Y') AS startDate, DATE_FORMAT(finishDate, '%d.%m.%Y') AS finishDate, status " +
//                             "FROM booksdb.`order` " +
//                             "JOIN booksdb.librarian ON Librarian_id = booksdb.librarian.id " +
//                             "JOIN booksdb.student ON Student_id=booksdb.student.id " +
//                             "JOIN booksdb.book ON Book_id=booksdb.book.id " +
//                             "WHERE student.fullName LIKE ? " +
//                             "AND book.title LIKE ? " +
//
//                             "  AND startDate BETWEEN STR_TO_DATE(?, '%d.%m.%Y' ) \n" +
//                             "  AND STR_TO_DATE(?, '%d.%m.%Y')" +
//
//                             "  AND finishDate BETWEEN STR_TO_DATE(?, '%d.%m.%Y' ) \n" +
//                             "  AND STR_TO_DATE(?, '%d.%m.%Y') " +
//
//                             "AND `order`.status LIKE ? AND librarian.fullName = ?")) {
//
//
//            Calendar date = Calendar.getOurInstance();
//            date.setTime(new Date());
//            Format f = new SimpleDateFormat("dd.MM.yyyy");
//            date.add(Calendar.YEAR, 1);
//
//            String currentDatePlusOneYear = f.format(date.getTime());
//
//
//            preparedStatement.setString(1, "%" + order.getStudent().getFullName() + "%");
//            preparedStatement.setString(2, "%" + order.getBook().getTitle() + "%");
//
//            if (order.getStartDate().isEmpty()) {
//                preparedStatement.setString(3, "01.01.1970");
//                preparedStatement.setString(4, currentDatePlusOneYear);
//            } else {
//                preparedStatement.setString(3, order.getStartDate());
//                preparedStatement.setString(4, order.getStartDate());
//            }
//
//            if (order.getFinishDate().isEmpty()) {
//                preparedStatement.setString(5, "01.01.1970");
//                preparedStatement.setString(6, currentDatePlusOneYear);
//            } else {
//                preparedStatement.setDate(5, order.getFinishDate());
//                preparedStatement.setString(6, order.getFinishDate());
//            }
//
//            if (order.getStatus().isEmpty())
//                preparedStatement.setString(7, "%%");
//            else
//                preparedStatement.setString(7, order.getStatus());
//
//
//            preparedStatement.setString(8, order.getLibrarian().getFullName());
//
//
//            try (ResultSet orderResultSet = preparedStatement.executeQuery()) {
//                return getOrdersFromResultSet(orderResultSet);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return new ArrayList<>();
    }

}
