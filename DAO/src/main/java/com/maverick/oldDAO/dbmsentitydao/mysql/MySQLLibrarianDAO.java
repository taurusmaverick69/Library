package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Book;
import com.maverick.domain.Librarian;
import com.maverick.domain.Order;
import com.maverick.domain.Student;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.LibrarianDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLLibrarianDAO implements LibrarianDAO {

    private final static String INSERT_LIBRARIAN = "INSERT INTO booksdb.librarian (full_name, password) VALUES (?, md5(?))";
    private final static String GET_LIBRARIANS = "SELECT * FROM booksdb.librarian";
    private final static String GET_ORDERS = "SELECT o.id, s.fullName, b.title, start_date, finishDate, status FROM booksdb.`order` o , booksdb.book b, booksdb.student s WHERE Student_id = s.id AND  Book_id = b.id AND Librarian_id = ?";
    private final static String CHECK_PASSWORD = "SELECT password FROM booksdb.librarian WHERE  full_name = ?";

    @Override
    public boolean insertLibrarian(Librarian librarian) {
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
    public boolean deleteLibrarian(Librarian librarian) {
        return false;
    }

    @Override
    public List<Librarian> selectLibrarians() {
        List<Librarian> librarians = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet librarianResultSet = statement.executeQuery(GET_LIBRARIANS);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERS)) {

            List<Order> orders = new ArrayList<>();
            while (librarianResultSet.next()) {

                preparedStatement.setInt(1, librarianResultSet.getInt("id"));
                ResultSet orderResultSet = preparedStatement.executeQuery();

                while (orderResultSet.next()) {

                    Student student = new Student();
                    student.setFullName(orderResultSet.getString("s.fullName"));

                    Book book = new Book();
                    book.setTitle(orderResultSet.getString("b.title"));

                    Order order = new Order(
                            orderResultSet.getInt("id"),
                            student,
                            book,
                            new Date(orderResultSet.getDate("startDate").getTime()),
                            new Date(orderResultSet.getDate("finishDate").getTime()),
                            orderResultSet.getString("status")
                    );
                    orders.add(order);
                }

                Librarian librarian = new Librarian(
                        librarianResultSet.getInt("id"),
                        librarianResultSet.getString("fullName"),
                        librarianResultSet.getString("password"),
                        orders);

                librarians.add(librarian);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return librarians;
    }

    @Override
    public boolean updateLibrarian(Librarian librarian) {
        return false;
    }

    @Override
    public boolean checkPassword(Librarian librarian) {

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     CHECK_PASSWORD)) {

            preparedStatement.setString(1, librarian.getFullName());
            try (ResultSet passwordsResultSet = preparedStatement.executeQuery()) {
                passwordsResultSet.next();
                if (passwordsResultSet.getString("password").equals(librarian.getPassword()))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
