package com.maverick.service;

import com.maverick.domain.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(int id);

    void save(Student student);

    void delete(int id);

}
