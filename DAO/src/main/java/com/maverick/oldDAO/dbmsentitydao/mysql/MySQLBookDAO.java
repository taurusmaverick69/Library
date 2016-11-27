package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Author;
import com.maverick.domain.Book;
import com.maverick.domain.Genre;
import com.maverick.domain.Publisher;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.BookDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBookDAO implements BookDAO {

    private final static String INSERT_BOOK = "INSERT INTO booksdb.book (Author_id, title, publishing_year, Genre_id, Publisher_id, amount) VALUES (?,?,?,?,?,?)";
    private final static String DELETE_BOOK = "DELETE FROM booksdb.book WHERE id = ?";
    private final static String SELECT_BOOKS = "CALL booksdb.selectBooks()";
    private final static String UPDATE_BOOK = "UPDATE booksdb.book SET Author_id = ?, title = ?, publishing_year = ?, Genre_id = ?, Publisher_id = ?, amount = ? WHERE id = ?";
    private final static String SEARCH_BOOK = "CALL booksdb.searchBook(?,?,?,?,?,?)";
    private final static String GET_AMOUNT = "SELECT amount FROM booksdb.book WHERE title = ?";
    private final static String IS_ORDERED = "SELECT Book_id FROM booksdb.`order`";

    @Override
    public boolean insertBook(Book book) {


        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK)) {

            preparedStatement.setInt(1, book.getAuthor().getId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getPublishingYear());
            preparedStatement.setInt(4, book.getGenre().getId());
            preparedStatement.setInt(5, book.getPublisher().getId());
            preparedStatement.setInt(6, book.getAmount());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteBook(Book book) {


        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)
        ) {
            preparedStatement.setInt(1, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Book> selectBooks() {

        List<Book> books = new ArrayList<>();


        try (Connection connection = MySQLDAOFactory.createConnection();
             CallableStatement callableStatement = connection.prepareCall(SELECT_BOOKS);
             ResultSet bookResultSet = callableStatement.executeQuery()) {

            while (bookResultSet.next()) {

                Author author = new Author();
                author.setFullName(bookResultSet.getString("fullName"));

                Genre genre = new Genre();
                genre.setName(bookResultSet.getString("genre.name"));

                Publisher publisher = new Publisher();
                publisher.setName(bookResultSet.getString("publisher.name"));

                books.add(new Book(
                        bookResultSet.getInt("id"),
                        author,
                        bookResultSet.getString("title"),
                        bookResultSet.getInt("publishingYear"),
                        genre,
                        publisher,
                        bookResultSet.getInt("amount")));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean updateBook(Book book) {

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)) {

            preparedStatement.setInt(1, book.getAuthor().getId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getPublishingYear());
            preparedStatement.setInt(4, book.getGenre().getId());
            preparedStatement.setInt(5, book.getPublisher().getId());
            preparedStatement.setInt(6, book.getAmount());
            preparedStatement.setInt(7, book.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Book> searchBook(Book book) {

        List<Book> books = new ArrayList<>();


        try (Connection connection = MySQLDAOFactory.createConnection();
             CallableStatement callableStatement = connection.prepareCall(SEARCH_BOOK)) {

            callableStatement.setString(1, "%" + book.getAuthor().getFullName() + "%");
            callableStatement.setString(2, "%" + book.getTitle() + "%");

            if (book.getPublishingYear() == -1)
                callableStatement.setString(3, "%%");
            else
                callableStatement.setString(3, "%" + book.getPublishingYear() + "%");

            callableStatement.setString(4, "%" + book.getGenre().getName() + "%");
            callableStatement.setString(5, "%" + book.getPublisher().getName() + "%");

            if (book.getAmount() == -1)
                callableStatement.setString(6, "%%");
            else
                callableStatement.setString(6, "%" + book.getAmount() + "%");

            try (ResultSet bookResultSet = callableStatement.executeQuery()) {
                while (bookResultSet.next()) {

                    Author author = new Author();
                    author.setFullName(bookResultSet.getString("fullName"));

                    Genre genre = new Genre();
                    genre.setName(bookResultSet.getString("genre.name"));

                    Publisher publisher = new Publisher();
                    publisher.setName(bookResultSet.getString("publisher.name"));

                    books.add(new Book(bookResultSet.getInt("id"), author, bookResultSet.getString("title"), bookResultSet.getInt("publishingYear"), genre, publisher, bookResultSet.getInt("amount")));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int selectAmount(Book book) {
        int amount;


        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_AMOUNT)) {

            preparedStatement.setString(1, book.getTitle());

            try (ResultSet amountResultSet = preparedStatement.executeQuery()) {
                amountResultSet.next();
                amount = amountResultSet.getInt("amount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return amount;
    }

    @Override
    public boolean isOrdered(Book book) {


        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(IS_ORDERED)) {

            while (resultSet.next()) {
                if (book.getId() == resultSet.getInt("Book_id"))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
