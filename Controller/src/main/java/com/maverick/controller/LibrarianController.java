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

    @RequestMapping
    public List<Librarian> findAll() {
        return librarianService.findAll();
    }

    @RequestMapping("/{id}")
    public Librarian findById(@PathVariable("id") int id) {
        return librarianService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@ModelAttribute("book") Librarian librarian) {
        librarianService.save(librarian);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        librarianService.delete(id);
    }
}
