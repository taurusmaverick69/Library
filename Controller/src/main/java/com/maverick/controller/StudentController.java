package com.maverick.controller;

import com.maverick.domain.Student;
import com.maverick.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @RequestMapping("/{id}")
    public Student findById(@PathVariable("id") int id) {
        return studentService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@ModelAttribute("book") Student student) {
        studentService.save(student);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        studentService.delete(id);
    }
}
