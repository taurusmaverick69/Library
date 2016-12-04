package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Author;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAuthorDAO implements AuthorDAO {

    private final static String INSERT_AUTHOR = "INSERT INTO booksdb.author (full_name, years_of_life) VALUES (?,?)";
    private final static String DELETE_AUTHOR = "DELETE FROM booksdb.author WHERE id = ?";
    private final static String GET_AUTHORS = "SELECT * FROM booksdb.author";

    @Override
    public boolean insertAuthor(Author author) {

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHOR)) {

            preparedStatement.setString(1, author.getFullName());
            preparedStatement.setString(2, author.getYearsOfLife());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAuthor(Author author) {


        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR)) {
            preparedStatement.setInt(1, author.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Author> selectAuthors() {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet authorResultSet = statement.executeQuery(GET_AUTHORS)) {

            while (authorResultSet.next())
                authors.add(new Author(
                        authorResultSet.getInt("id"),
                        authorResultSet.getString("fullName"),
                        authorResultSet.getString("yearsOfLife")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public boolean updateAuthor(Author author) {
        return false;
    }
}
