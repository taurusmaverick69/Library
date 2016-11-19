package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Group;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.GroupDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLGroupDAO implements GroupDAO {

    private final static String INSET_GROUP = "INSERT INTO booksdb.`group` (name) VALUES (?)";
    private final static String GET_GROUPS = "SELECT * FROM booksdb.`group`";

    @Override
    public boolean insertGroup(Group group) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSET_GROUP)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteGroup(Group group) {
        return false;
    }

    @Override
    public List<Group> selectGroups() {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet groupResultSet = statement.executeQuery(GET_GROUPS)) {

            while (groupResultSet.next())
                groups.add(new Group(groupResultSet.getInt("id"), groupResultSet.getString("name")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    @Override
    public boolean updateGroup(Group group) {
        return false;
    }

}
