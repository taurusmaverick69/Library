package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Group;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.GroupDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLGroupDAO implements GroupDAO {

    private final static String GROUP_ID = "group.id";
    private final static String GROUP_NAME = "group.name";

    private final static String FIND_ALL = "SELECT * FROM `group`";
    private final static String FIND_BY_ID = "SELECT * FROM `group` WHERE id = ?";
    private final static String INSERT_GROUP = "INSERT INTO `group` VALUES (DEFAULT,?)";

    @Override
    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet groupResultSet = statement.executeQuery(FIND_ALL)) {
            while (groupResultSet.next())
                groups.add(new Group(groupResultSet.getInt(GROUP_ID), groupResultSet.getString(GROUP_NAME)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    @Override
    public Group findById(int id) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt(GROUP_ID));
                group.setName(resultSet.getString(GROUP_NAME));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Group group) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GROUP)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Group group) {
        return false;
    }

    @Override
    public boolean delete(Group group) {
        return false;
    }
}
