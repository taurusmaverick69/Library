package com.maverick.controller;

import com.maverick.domain.Author;
import com.maverick.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("publisher")
public class PublisherController {

    @Autowired
    private AuthorService authorService;

    //  @RequestMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }


}
