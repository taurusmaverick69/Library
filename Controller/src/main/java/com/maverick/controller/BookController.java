package com.maverick.controller;

import com.maverick.domain.Book;
import com.maverick.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable("id") int id) {
        return bookService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Book book) {
        bookService.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        bookService.delete(id);
    }
}