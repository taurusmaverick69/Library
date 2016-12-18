package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Student;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.GroupDAO;
import com.maverick.oldDAO.entitydao.StudentDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLStudentDAO implements StudentDAO {

    private final static String STUDENT_ID = "student.id";
    private final static String STUDENT_FULL_NAME = "student.full_name";
    private final static String STUDENT_LIBRARY_CARD = "student.library_card";
    private final static String STUDENT_GROUP_ID = "student.Group_id";

    private final static String FIND_ALL = "SELECT * FROM student";
    private final static String FIND_BY_ID = "SELECT * FROM student WHERE id = ?";
    private final static String INSERT_STUDENT = "INSERT INTO booksdb.student (full_name, library_card, Group_id) VALUES (?,?,?)";
    private final static String DELETE_STUDENT = "DELETE FROM booksdb.student WHERE id = ?";

    private GroupDAO groupDAO;

    @Override
    public List<Student> findAll() {

        groupDAO = new MySQLGroupDAO();

        List<Student> students = new ArrayList<>();
        try (ResultSet resultSet = MySQLDAOFactory.createConnection().createStatement().executeQuery(FIND_ALL)) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(STUDENT_ID));
                student.setFullName(resultSet.getString(STUDENT_FULL_NAME));
                student.setLibraryCard(resultSet.getString(STUDENT_LIBRARY_CARD));
                student.setGroup(groupDAO.findById(resultSet.getInt(STUDENT_GROUP_ID)));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student findById(int id) {

        groupDAO = new MySQLGroupDAO();

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(STUDENT_ID));
                student.setFullName(resultSet.getString(STUDENT_FULL_NAME));
                student.setLibraryCard(resultSet.getString(STUDENT_LIBRARY_CARD));
                student.setGroup(groupDAO.findById(resultSet.getInt(STUDENT_GROUP_ID)));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Student student) {

        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setString(2, student.getLibraryCard());
            preparedStatement.setInt(3, student.getGroup().getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Student student) {
        return false;
    }
}
