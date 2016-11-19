package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Genre;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.GenreDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLGenreDAO implements GenreDAO {

    private final static String INSERT_GENRE = "INSERT INTO booksdb.genre (name) VALUES (?)";
    private final static String GET_GENRES = "SELECT * FROM booksdb.genre";

    @Override
    public boolean insertGenre(Genre genre) {
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
    public boolean deleteGenre(Genre genre) {
        return false;
    }

    @Override
    public List<Genre> selectGenres() {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet genreResultSet = statement.executeQuery(GET_GENRES)) {
            while (genreResultSet.next())
                genres.add(new Genre(genreResultSet.getInt("id"), genreResultSet.getString("name")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public boolean updateGenre(Genre genre) {
        return false;
    }
}
