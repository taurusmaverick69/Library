package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Genre;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.GenreDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLGenreDAO implements GenreDAO {

    private final static String GENRE_ID = "genre.id";
    private final static String GENRE_NAME = "genre.name";

    private final static String FIND_ALL = "SELECT * FROM genre";
    private final static String FIND_BY_ID = "SELECT * FROM genre WHERE id = ?";
    private final static String INSERT_GENRE = "INSERT INTO genre VALUES (DEFAULT,?)";

    @Override
    public List<Genre> findAll() {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet genreResultSet = statement.executeQuery(FIND_ALL)) {
            while (genreResultSet.next()) {
                Genre genre = new Genre();
                genre.setId(genreResultSet.getInt(GENRE_ID));
                genre.setName(genreResultSet.getString(GENRE_NAME));
                genres.add(genre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public Genre findById(int id) {

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Genre genre = new Genre();
            genre.setId(resultSet.getInt(GENRE_ID));
            genre.setName(resultSet.getString(GENRE_NAME));
            return genre;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Genre genre) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GENRE)) {
            preparedStatement.setString(1, genre.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Genre genre) {
        return false;
    }

    @Override
    public boolean delete(Genre genre) {
        return false;
    }
}
