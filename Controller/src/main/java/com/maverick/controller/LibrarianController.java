package com.maverick.controller;

import com.maverick.domain.Librarian;
import com.maverick.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("librarian")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @GetMapping
    public List<Librarian> findAll() {
        return librarianService.findAll();
    }

    @GetMapping("/{id}")
    public Librarian findById(@PathVariable("id") int id) {
        return librarianService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Librarian librarian) {
        librarianService.save(librarian);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        librarianService.delete(id);
    }
}
