package com.maverick.oldDAO.entitydao;

import com.maverick.domain.Student;

import java.util.List;

public interface StudentDAO {

    List<Student> findAll();

    Student findById(int id);

    boolean save(Student student);

    boolean update(Student student);

    boolean delete(Student student);
}
