package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Publisher;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.PublisherDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLPublisherDAO implements PublisherDAO {

    private final static String INSERT_PUBLISHER = "INSERT INTO booksdb.publisher (name) VALUES (?)";
    private final static String GET_PUBLISHERS = "SELECT * FROM booksdb.publisher";

    @Override
    public boolean insertPublisher(Publisher publisher) {
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
    public boolean deletePublisher(Publisher publisher) {
        return false;
    }

    @Override
    public List<Publisher> selectPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet publisherResultSet = statement.executeQuery(GET_PUBLISHERS)) {
            while (publisherResultSet.next()) {
                Publisher publisher = new Publisher(
                        publisherResultSet.getInt("id"),
                        publisherResultSet.getString("name"));
                publishers.add(publisher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publishers;
    }

    @Override
    public boolean updatePublisher(Publisher publisher) {
        return false;
    }
}
