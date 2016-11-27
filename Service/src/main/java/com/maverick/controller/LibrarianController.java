package com.maverick.controller;

import com.maverick.domain.Author;
import com.maverick.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("librarian")
public class LibrarianController {

    @Autowired
    private AuthorService authorService;

    public List<Author> findAll() {
        return authorService.findAll();
    }

}
