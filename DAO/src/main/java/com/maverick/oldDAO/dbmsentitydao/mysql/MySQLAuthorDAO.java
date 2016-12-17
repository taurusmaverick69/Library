package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Author;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.AuthorDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAuthorDAO implements AuthorDAO {

    private final static String AUTHOR_ID = "author.id";
    private final static String AUTHOR_FULL_NAME = "author.full_name";
    private final static String AUTHOR_YEARS_OF_LIFE = "author.years_of_life";

    private final static String FIND_ALL = "SELECT * FROM author";
    private final static String FIND_BY_ID = "SELECT * FROM author WHERE id = ?";
    private final static String INSERT_AUTHOR = "INSERT INTO author VALUES (DEFAULT,?,?)";
    private final static String DELETE_AUTHOR = "DELETE FROM author WHERE id = ?";

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet authorResultSet = statement.executeQuery(FIND_ALL)) {

            while (authorResultSet.next()) {
                Author author = new Author();
                author.setId(authorResultSet.getInt(AUTHOR_ID));
                author.setFullName(authorResultSet.getString(AUTHOR_FULL_NAME));
                author.setYearsOfLife(authorResultSet.getString(AUTHOR_YEARS_OF_LIFE));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author findById(int id) {

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt(AUTHOR_ID));
                author.setFullName(resultSet.getString(AUTHOR_FULL_NAME));
                author.setYearsOfLife(resultSet.getString(AUTHOR_YEARS_OF_LIFE));
                return author;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean save(Author author) {
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
    public boolean update(Author author) {
        return false;
    }

    @Override
    public boolean delete(Author author) {
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
}
