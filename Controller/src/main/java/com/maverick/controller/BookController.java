package com.maverick.controller;

import com.maverick.domain.Book;
import com.maverick.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    //   @RequestMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

}
