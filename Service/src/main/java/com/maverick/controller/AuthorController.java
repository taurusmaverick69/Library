package com.maverick.controller;

import com.maverick.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/")
    public String findAll() {
        authorService.findAll();
        return "AuthorController.findAll";
    }

}
