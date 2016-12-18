package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Publisher;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.PublisherDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLPublisherDAO implements PublisherDAO {

    private final static String PUBLISHER_ID = "publisher.id";
    private final static String PUBLISHER_NAME = "publisher.name";

    private final static String INSERT_PUBLISHER = "INSERT INTO publisher VALUES (DEFAULT, ?)";
    private final static String FIND_BY_ID = "SELECT * FROM publisher WHERE id = ?";
    private final static String FIND_ALL = "SELECT * FROM publisher";

    @Override
    public List<Publisher> findAll() {
        List<Publisher> publishers = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet publisherResultSet = statement.executeQuery(FIND_ALL)) {
            while (publisherResultSet.next()) {

                Publisher publisher = new Publisher();
                publisher.setId(publisherResultSet.getInt(PUBLISHER_ID));
                publisher.setName(publisherResultSet.getString(PUBLISHER_NAME));
                publishers.add(publisher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    @Override
    public Publisher findById(int id) {

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(resultSet.getInt(PUBLISHER_ID));
                publisher.setName(resultSet.getString(PUBLISHER_NAME));
                return publisher;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Publisher publisher) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER)) {
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Publisher publisher) {
        return false;
    }

    @Override
    public boolean delete(Publisher publisher) {
        return false;
    }
}
