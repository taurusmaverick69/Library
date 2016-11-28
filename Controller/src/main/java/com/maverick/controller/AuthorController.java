package com.maverick.controller;

import com.maverick.domain.Author;
import com.maverick.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @RequestMapping("/{id}")
    public Author findById(@PathVariable("id") int id) {
        return authorService.findById(id);
    }

    @RequestMapping("/save")
    public void save(Author author) {
        authorService.save(author);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        authorService.delete(id);
    }

}