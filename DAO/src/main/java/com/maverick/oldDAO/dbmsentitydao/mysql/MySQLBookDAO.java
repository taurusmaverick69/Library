package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Author;
import com.maverick.domain.Book;
import com.maverick.domain.Genre;
import com.maverick.domain.Publisher;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;
import com.maverick.oldDAO.entitydao.BookDAO;
import com.maverick.oldDAO.entitydao.GenreDAO;
import com.maverick.oldDAO.entitydao.PublisherDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBookDAO implements BookDAO {

    private final static String BOOK_ID = "book.id";
    private final static String BOOK_AUTHOR_ID = "book.Author_id";
    private final static String BOOK_TITLE = "book.title";
    private final static String BOOK_PUBLISHING_YEAR = "book.publishing_year";
    private final static String BOOK_GENRE_ID = "book.Genre_id";
    private final static String BOOK_PUBLISHER_ID = "book.Publisher_id";
    private final static String BOOK_AMOUNT = "book.amount";

    private final static String FIND_ALL = "SELECT * FROM book";
    private final static String FIND_BY_ID = "SELECT * FROM book WHERE id = ?";
    private final static String INSERT_BOOK = "INSERT INTO book VALUES (DEFAULT,?,?,?,?,?,?)";
    private final static String UPDATE_BOOK = "UPDATE book SET Author_id = ?, title = ?, publishing_year = ?, Genre_id = ?, Publisher_id = ?, amount = ? WHERE id = ?";
    private final static String DELETE_BOOK = "DELETE FROM book WHERE id = ?";
    private final static String GET_AMOUNT = "SELECT amount FROM book WHERE title = ?";

    private AuthorDAO authorDAO;
    private GenreDAO genreDAO;
    private PublisherDAO publisherDAO;

    @Override
    public List<Book> findAll() {

        authorDAO = new MySQLAuthorDAO();
        genreDAO = new MySQLGenreDAO();
        publisherDAO = new MySQLPublisherDAO();

        List<Book> books = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
             CallableStatement callableStatement = connection.prepareCall(FIND_ALL);
             ResultSet resultSet = callableStatement.executeQuery()) {

            while (resultSet.next()) {

                Book book = new Book();
                book.setId(resultSet.getInt(BOOK_ID));

                Author author = new Author();
                author.setId(resultSet.getInt(BOOK_AUTHOR_ID));

                Genre genre = new Genre();
                genre.setId(resultSet.getInt(BOOK_GENRE_ID));

                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt(BOOK_PUBLISHER_ID));

                book.setAuthor(author);
                book.setTitle(resultSet.getString(BOOK_TITLE));
                book.setPublishingYear(resultSet.getInt(BOOK_PUBLISHING_YEAR));
                book.setGenre(genre);
                book.setPublisher(publisher);
                book.setAmount(resultSet.getInt(BOOK_AMOUNT));

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        books.forEach(book -> {
            book.setAuthor(authorDAO.findById(book.getAuthor().getId()));
            book.setGenre(genreDAO.findById(book.getGenre().getId()));
            book.setPublisher(publisherDAO.findById(book.getPublisher().getId()));
        });

        return books;
    }

    @Override
    public Book findById(int id) {

        Book book = new Book();

        authorDAO = new MySQLAuthorDAO();
        genreDAO = new MySQLGenreDAO();
        publisherDAO = new MySQLPublisherDAO();

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                book.setId(resultSet.getInt(BOOK_ID));

                Author author = new Author();
                author.setId(resultSet.getInt(BOOK_AUTHOR_ID));
                book.setAuthor(author);

                Genre genre = new Genre();
                genre.setId(resultSet.getInt(BOOK_GENRE_ID));
                book.setGenre(genre);

                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt(BOOK_PUBLISHER_ID));
                book.setPublisher(publisher);

                book.setTitle(resultSet.getString(BOOK_TITLE));
                book.setPublishingYear(resultSet.getInt(BOOK_PUBLISHING_YEAR));
                book.setAmount(resultSet.getInt(BOOK_AMOUNT));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        book.setAuthor(authorDAO.findById(book.getAuthor().getId()));
        book.setGenre(genreDAO.findById(book.getGenre().getId()));
        book.setPublisher(publisherDAO.findById(book.getGenre().getId()));

        return book;
    }

    @Override
    public boolean save(Book book) {


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
    public boolean update(Book book) {

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
    public boolean delete(int id) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public int selectAmount(Book book) {
        int amount;

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_AMOUNT)) {

            preparedStatement.setString(1, book.getTitle());

            try (ResultSet amountResultSet = preparedStatement.executeQuery()) {
                amountResultSet.next();
                amount = amountResultSet.getInt(BOOK_AMOUNT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return amount;
    }
}
