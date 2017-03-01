package com.maverick.controller;

import com.maverick.domain.Author;
import com.maverick.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable("id") int id) {
        return authorService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Author author) {
        authorService.save(author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        authorService.delete(id);
    }
}