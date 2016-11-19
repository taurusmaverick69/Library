package com.maverick.oldDAO.dbmsentitydao.mysql;

import com.maverick.domain.Group;
import com.maverick.domain.Student;
import com.maverick.oldDAO.dbmsdaofactory.MySQLDAOFactory;
import com.maverick.oldDAO.entitydao.StudentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLStudentDAO implements StudentDAO {

    private final static String INSERT_STUDENT = "INSERT INTO booksdb.student (fullName, libraryCard, Group_id) VALUES (?,?,?)";
    private final static String DELETE_STUDENT = "DELETE FROM booksdb.student WHERE id = ?";
    private final static String GET_STUDENTS = "SELECT student.id, fullName, libraryCard, `group`.name FROM booksdb.student, booksdb.`group` WHERE `group`.id = Group_id";

    @Override
    public boolean insertStudent(Student student) {

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
    public boolean deleteStudent(Student student) {
        try (Connection connection = MySQLDAOFactory.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Student> selectStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = MySQLDAOFactory.createConnection();
             Statement statement = connection.createStatement();
             ResultSet studentResultSet = statement.executeQuery(GET_STUDENTS)) {
            while (studentResultSet.next()) {
                Group group = new Group();
                group.setName(studentResultSet.getString("name"));
                Student student = new Student(
                        studentResultSet.getInt("id"),
                        studentResultSet.getString("fullName"),
                        studentResultSet.getString("libraryCard"),
                        group);
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }
}
