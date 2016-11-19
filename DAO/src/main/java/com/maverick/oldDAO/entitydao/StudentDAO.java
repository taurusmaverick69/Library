package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Student;

import java.util.List;

public interface StudentDAO {

    boolean insertStudent(Student student);

    boolean deleteStudent(Student student);

    List<Student> selectStudents();

    boolean updateStudent(Student student);


}
