package com.maverick.controller;

import com.maverick.domain.Author;
import com.maverick.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/all")
    public String findAll() {
        List<Author> list = authorService.findAll();
        System.out.println("list = " + list);
        return "AuthorController.findAll";
    }

}
